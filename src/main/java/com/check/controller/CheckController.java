package com.check.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

@Controller
@RequestMapping(value="/checkbing")
public class CheckController {
  
	private static Random random = new Random();
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	static {
		 cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
//		cs.setColorFactory(new ColorFactory() {
//			public Color getColor(int x) {
//				int[] c = new int[3];
//				int i = random.nextInt(c.length);
//				for (int fi = 0; fi < c.length; fi++) {
//					if (fi == i) {
//						c[fi] = random.nextInt(71);
//					} else {
//						c[fi] = random.nextInt(256);
//					}
//				}
//				return new Color(c[0], c[1], c[2]);
//			}
//		});
		RandomFontFactory ff = new RandomFontFactory();
		ff.setMinSize(40);
		ff.setMaxSize(40);
		cs.setFontFactory(ff);
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("0123456789");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	};
	@RequestMapping(value="Image")
	public void check(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		int i=random.nextInt(5);
		switch (i) {
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
		String checkImgStr = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		/**
		 * ´æ´¢session
		 */
		session.setAttribute("code", checkImgStr.toUpperCase());
	}
	
	
}
