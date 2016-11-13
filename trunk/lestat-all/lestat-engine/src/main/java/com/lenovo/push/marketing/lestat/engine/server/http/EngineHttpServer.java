package com.lenovo.push.marketing.lestat.engine.server.http;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * An HTTP server that sends back the content of the received HTTP request in a
 * pretty plaintext form.
 */
public class EngineHttpServer {

	private static Logger logger = Logger.getLogger(EngineHttpServer.class);

	private final int port;
	private ServerBootstrap bootstrap;
	
	@Autowired
	private EngineHttpServerPipelineFactory engineHttpServerPipelineFactory;

	public EngineHttpServer(int port) {
		this.port = port;
	}

	// private ExecutorService threadPool;

	@PreDestroy
	public void preDestory() {
		if (bootstrap != null) {
			bootstrap.shutdown();
			logger.info("engine http server shut down: " + port);
		}
	}

	public void init() {
		// try {
		// threadPool = Executors.newFixedThreadPool(1);
		// threadPool.execute(this);
		// } catch (Exception e) {
		// logger.error("init: " + e.getMessage());
		// }
		run();
	}

	// @Override
	public void run() {
		// Configure the server.
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		// Enable TCP_NODELAY to handle pipelined requests without latency.
		bootstrap.setOption("child.tcpNoDelay", true);

		// Set up the event pipeline factory.
		//bootstrap.setPipelineFactory(new EngineHttpServerPipelineFactory());
		bootstrap.setPipelineFactory(engineHttpServerPipelineFactory);

		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(port));

		logger.info("engine http server started: " + port);
	}

	// public static void main(String[] args) {
	// int port;
	// if (args.length > 0) {
	// port = Integer.parseInt(args[0]);
	// } else {
	// port = 8081;
	// }
	// new EngineHttpServer(port).run();
	// }

}
