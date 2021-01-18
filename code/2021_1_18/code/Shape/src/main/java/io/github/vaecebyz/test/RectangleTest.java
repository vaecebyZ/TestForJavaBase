package main.java.io.github.vaecebyz.test;

import java.util.Scanner;
import main.java.io.github.vaecebyz.domain.*;

public class RectangleTest {
	//长宽判断
	static boolean isWidth = false; 

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		//长
		double length = getUserPrint(scanner); 

		//是否是非法输入
		while (length == -1) {
			length = getUserPrint(scanner);
		}

		//宽
		double width = getUserPrint(scanner);

		//是否是非法输入
		while (width == -1) {
			width = getUserPrint(scanner);
		}

		scanner.close();
		

		Rectangle rectangle = new Rectangle();

		rectangle.setLength(length);

		rectangle.setWidth(width);

		System.out.println("矩形的周长是：" + rectangle.getPerimerter());

		System.out.println("矩形的面积是：" + rectangle.getArea());

	}

	private static double getUserPrint(Scanner scanner) {

		//提示
		if (isWidth) {
			System.out.println("请输入矩形的宽：");
		} else {
			System.out.println("请输入矩形的长：");
		}

		String userPrintString = scanner.nextLine();

		//非空判断
		if (!userPrintString.isEmpty()) {
			
			//退出
			if(userPrintString.equals("q") || userPrintString.equals("Q")) {
				System.out.println("已退出");
				System.exit(0);
			}

			//处理异常和返回值
			try {
					//hasNextFloat
				double userPrintDouble = Double.valueOf(userPrintString);

				if (userPrintDouble > 0) {

					isWidth = true;

					return userPrintDouble;

				} else {
					System.out.println("请输入大于0的数");

				}

			} catch (Exception e) {

				System.out.println("请输入数字。");

			}

		} else {
			System.out.println("不能为空");

		}

		return -1;

	}

}
