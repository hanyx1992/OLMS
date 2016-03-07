package com.chenlu.olms.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class CheckcodeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 一、绘图
		 */
		/*
		 * step1,创建一个内存映像对象(画布)
		 * BufferedImage(宽度,高度,类型)
		 */
		BufferedImage image = 
			new BufferedImage(80,30,
					BufferedImage.TYPE_INT_RGB);
		/*
		 * step2,获得一个画笔
		 */
		Graphics g = image.getGraphics();
		/*
		 * step3,给笔设置颜色
		 */
		Random r = new Random();
		g.setColor(new Color(255,255,255));
		/*
		 * step4,给画布设置背景颜色
		 * fillRect(x,y,width,height)
		 */
		g.fillRect(0, 0, 80, 30);
		/*
		 * step5,绘图
		 */
		g.setColor(new Color(r.nextInt(255),
				r.nextInt(255),r.nextInt(255)));
		//Font(字体,风格,大小)
		g.setFont(new Font(null,Font.ITALIC,22));
		String number = getNumber(5);
		//将number绑订到session对象上
		HttpSession session = 
			request.getSession();
		session.setAttribute("number", number);
		//drawString(String,x,y) x,y是左下角的坐标
		g.drawString(number, 2, 23);
		//step6,加一些干扰线
		for(int i=0;i<6;i++){
			g.drawLine(r.nextInt(80), 
					r.nextInt(30), 
					r.nextInt(80), r.nextInt(30));
		}
		
		/*
		 * 二、将图片压缩并发送给浏览器
		 */
		//设置content-type消息头，告诉
		//浏览器返回的是图片
		response.setContentType("image/jpeg");
		//一定要获得字节输出流
		OutputStream ops = 
			response.getOutputStream();
		//write方法会对原始图片(image)按照
		//指定的压缩算法(jpeg)进行压缩，并且
		//将压缩之后的数据输出到指定的流(ops)。
		javax.imageio.ImageIO
		.write(image, "jpeg", ops);
		ops.close();
	}
	
	/*
	 * 生成一个验证码，该验证码由"A~Z","0~9"中
	随机选取5个字符组成
	 */
	private String getNumber(int size) {
		String rs = "";
		String strs = "ABCDEFGHIJKLMNOPQ" +
				"RSTUVWXYZ0123456789";
		Random r = new Random();
		for(int i=0;i<size;i++){
			rs += strs.charAt(r.nextInt(strs.length()));
		}
		return rs;
	}

}
