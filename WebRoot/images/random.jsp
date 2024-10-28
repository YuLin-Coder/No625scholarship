<%@ page autoFlush="false"  import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*" pageEncoding="GBK"%>

<%
//set Chinese Char 
//Cody by JarryLi@gmail.com;
//homepage:jiarry.126.com
request.setCharacterEncoding("GBK");
response.setCharacterEncoding("GBK");
response.setContentType("text/html; charset=GBK");
%>
<%
String chose="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

char display[]={'0',' ','0',' ','0',' ','0'},ran[]={'0','0','0','0'},temp;

Random rand=new Random();

for(int i=0;i<4;i++)
{

 temp=chose.charAt(rand.nextInt(chose.length()));

 display[i*2]=temp;

 ran[i]=temp;
 }
 
String random=String.valueOf(display);

session.setAttribute("random",String.valueOf(ran));
 %>
<%

        out.clear();
        response.setContentType("image/jpeg");
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        int width=75, height=25;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //以下填充背景颜色
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);
       //设置字体颜色
        g.setColor(Color.RED);
        Font font=new Font("Arial",Font.PLAIN,20);
        g.setFont(font);
        g.drawString(random,5,20);
        g.dispose();
        ServletOutputStream outStream = response.getOutputStream();
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
        encoder.encode(image);
        outStream.close();
        out.clear();
	  out = pageContext.pushBody();
   %>


