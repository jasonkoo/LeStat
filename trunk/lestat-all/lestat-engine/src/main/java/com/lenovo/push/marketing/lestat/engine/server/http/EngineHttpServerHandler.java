package com.lenovo.push.marketing.lestat.engine.server.http;

import static org.jboss.netty.handler.codec.http.HttpHeaders.is100ContinueExpected;
import static org.jboss.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders.Values;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.stereotype.Component;

import com.lenovo.push.marketing.lestat.engine.result.BaseJsonResult;

@Component
public class EngineHttpServerHandler extends SimpleChannelUpstreamHandler {

	static Logger logger = Logger.getLogger(EngineHttpServerHandler.class);

	// private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ',
	// 'W', 'o', 'r', 'l', 'd' };

	@Resource(name = "engineHttpRequestHandlerImpl")
	private EngineHttpRequestHandler engineHttpRequestHandler;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Object msg = e.getMessage();
		Channel ch = e.getChannel();
		String remoteAddress = ctx.getChannel().getRemoteAddress().toString();
		logger.debug("remote address: " + remoteAddress);

		if (msg instanceof HttpRequest) {
			HttpRequest req = (HttpRequest) msg;

			if (is100ContinueExpected(req)) {
				Channels.write(ctx, Channels.future(ch),
						new DefaultHttpResponse(HTTP_1_1, CONTINUE));
			}

			BaseJsonResult result = null;
			try {
				result = engineHttpRequestHandler.handleHttpRequest(req);
			} catch (Exception ex) {
				result = new BaseJsonResult();
				result.setMessage("exception occurs: " + ex.getMessage());
			}

			boolean keepAlive = isKeepAlive(req);
			HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
			String content = result.toJsonString();
			logger.debug("returned content: " + content);
			response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes()));
			response.setHeader(CONTENT_TYPE, "text/plain");
			response.setHeader(CONTENT_LENGTH, response.getContent()
					.readableBytes());

			if (!keepAlive) {
				ChannelFuture f = Channels.future(ch);
				f.addListener(ChannelFutureListener.CLOSE);
				Channels.write(ctx, f, response);
			} else {
				response.setHeader(CONNECTION, Values.KEEP_ALIVE);
				Channels.write(ctx, Channels.future(ch), response);
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// e.getCause().printStackTrace();
		logger.error(e.getCause().getMessage());
		e.getChannel().close();
	}
}
