package com.lm.smssender;

import android.util.Log;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class UdpThread implements Runnable {
	private static final String TAG = "UDPThread";
	private SmsService smsService = null;
	private Thread rthread = null;// 接收数据线程
	private DatagramSocket udpSocket = null;// 接收数据Socket
	int localPort = 50000;
	private DatagramPacket rPacket = null;
	private byte[] rBuffer = new byte[1024];// 接收数据缓存1024字节

	public UdpThread(SmsService smsService) {
		this.smsService = smsService;
	}

	public boolean start() {
		boolean result = false;

		String ipAddress = getIp();
		if (smsService != null) {
			smsService.displayNotificationMessage(ipAddress);
			smsService.broadCast("ipAddress", ipAddress);
		}

		// open socket
		try {
			if (udpSocket == null) {
				udpSocket = new DatagramSocket(localPort);
				Log.i(TAG, "udpSocket port:" + udpSocket.getLocalPort());

			}
			if (rPacket == null) {
				rPacket = new DatagramPacket(rBuffer, rBuffer.length);
			}
			result = true;
		} catch (SocketException se) {
			// udpScoket.close();
			udpSocket = null;
			System.out.println("open udp port error:" + se.getMessage());
		}

		// start thread
		if ((result = true) && (rthread == null)) {
			rthread = new Thread(this);
			rthread.start();
		}

		return result;
	}

	public boolean stop() {
		boolean result = false;

		if (rthread != null) {
			rthread = null;
		}

		if (udpSocket != null) {
			udpSocket.close();
			udpSocket = null;
		}

		return result;
	}

	@Override
	public void run() {
		while (Thread.currentThread() == rthread) {
			Log.i(TAG, "thread is running");
			try {
				recvData();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recvData() {
		try {
			udpSocket.receive(rPacket);
			Log.i(TAG, "data Received");
			processReceivedData(rPacket);
		} catch (IOException ie) {
			System.out.println("recvdata error:" + ie.getMessage());
		}
	}

	private void processReceivedData(DatagramPacket dataPacket) {
		byte[] recvdData = new byte[1024];
		String recvdString = null;
		String phoneNumber = null;
		String content = null;
		String sequence = null;
		int len;

		len = dataPacket.getLength();
		for (int i = 0; i < len; i++) {
			recvdData[i] = dataPacket.getData()[i];
		}
		if (len > 0) {
			recvdString = new String(recvdData);
			// trim the empty bytes at the end.
			recvdString = recvdString.trim();
			String[] items = recvdString.split(";;;");
			for (String item : items) {
				String[] parts = item.split("===");
				if (parts[0].equals("phonenumber")) {
					phoneNumber = parts[1];
				}
				if (parts[0].equals("content")) {
					content = parts[1];
				}
				if (parts[0].equals("sequence")) {
					sequence = parts[1];
				}
			}
			if ((phoneNumber != null) && (content != null)) {
				smsService.sendSMS(phoneNumber, content, sequence);
				Log.i(TAG, "SMS sent");
			}
		}
	}

	public void sendDataViaThread(final InetAddress toIpAddress,
			final int toPort, final String sData) {
		Thread sendingThread = new Thread() {
			public void run() {
				DatagramSocket udp = null;
				DatagramPacket packet;
				byte[] sbuffer;

				try {
					udp = new DatagramSocket();
					sbuffer = sData.getBytes();
					packet = new DatagramPacket(sbuffer, sbuffer.length,
							toIpAddress, toPort);
					udp.send(packet);
					Log.i(TAG, "Packet sent ok.");
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (udp != null) {
					udp.close();
					udp = null;
				}
				packet = null;
			}
		};

		sendingThread.start();
	}

	public void sendData(String toIpAddress, int toPort, String sData) {
		try {
			sendDataViaThread(InetAddress.getByName(toIpAddress), toPort, sData);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendData(String sData) {
		sendDataViaThread(rPacket.getAddress(), rPacket.getPort(), sData);
	}

	public static String getIp() {
		String localip = "127.0.0.1";
		String netip = null;
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress()
							&& !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

}
