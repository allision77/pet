package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//注释配置Servlet
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
 private static final long serialVersionUID = 1L;
 private int width = 80; // 验证码图片的默认宽度
 private int height = 20; // 验证码图片的默认高度
 private int codeCount = 4; // 验证码图片的字符数
 private int x = 16;
 private int fontHeight=16;
 private int codeY=18;
 private final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
   'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
   'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
   '7', '8', '9' };
  
 protected void doGet(HttpServletRequest request,
   HttpServletResponse response) throws ServletException,
   java.io.IOException {
  //构造一个类型为预定义图像类型之一的BufferedImage设置图像的宽、高和类型（TYPE_INT_RGB）
  BufferedImage Img = new BufferedImage(width, height,
    BufferedImage.TYPE_INT_RGB);
   
  // 返回Graphics2D，Graphics2D类扩展自Graphics 类，以提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
  Graphics g = Img.getGraphics();
   
  Random random = new Random();
  // 将图像填充为白色
  g.setColor(Color.WHITE);
  //填充指定的矩形。x、y坐标均为0，宽为Ϊwidth,高为Ϊheight
  g.fillRect(0, 0, width, height);
  // 创建字体，字体大小应该根据图片的高度来定
  Font font = new Font("Times new Roman", Font.PLAIN, fontHeight);
  
  g.setColor(Color.black);
  g.setFont(font);
  Color juneFont = new Color(153, 204, 102);
  // 随机产生130条干扰线，不易被其他程序探测
  g.setColor( juneFont );
  for (int i = 0; i < 130; i++) {
    
   //返回伪随机数
   int x= random.nextInt(width);
   int y = random.nextInt(height);
   int xl = random.nextInt(16);  //80/5=16
   int yl = random.nextInt(16);
   //在此图形上下文的坐标系中，使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线
   g.drawLine(x, y, x + xl, y + yl);
    
  }
  // randomCode用于保存随机产生的验证码，以便用户登录后进行验证，线程安全的可变字符序列
  StringBuffer randomCode = new StringBuffer();
  // 随机产生codeCount数字的验证码
  for (int i = 0; i < codeCount; i++) {
   //返回 char 参数的字符串表示形式
   String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
    
   // 用随机产生的颜色将验证码绘制到图像中
   //创建具有指定红色、绿色和蓝色值的不透明的 sRGB 颜色，这些值都在 (0 - 255) 的范围内
   g.setColor(new Color(20 + random.nextInt(110), 20 + random
     .nextInt(110), 20 + random.nextInt(110)));
    
   //使用此图形上下文的当前字体和颜色绘制由指定 string 给定的文本。最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
   g.drawString(strRand, (i + 1) * x-4, codeY);
   randomCode.append(strRand);
  }
  HttpSession session = request.getSession(); // 将四位数字的验证码保存到Session中
  session.setAttribute("realcode", randomCode.toString());
  // 禁止浏览器缓存
  response.setHeader("Pragma", "no-cache");    //HTTP   1.0
  response.setHeader("Cache-Control", "no-cache");//HTTP   1.1
  response.setDateHeader("Expires", 0);      //在代理服务器端防止缓冲
  response.setContentType("image/gif");      //设置正被发往客户端的相应的内容类型
   
  // 将图像输出到Servlet输出流中,ServletOutputStream提供了向客户端的相应的内容类型
  ServletOutputStream sos = response.getOutputStream();
  ImageIO.write(Img, "gif", sos);           //使用支持给定格式的任意 ImageWriter ，将一个图像写入 OutputStream
  sos.flush();                    //刷新此输出流并强制写出所有的输出字节
  sos.close();
 }
}