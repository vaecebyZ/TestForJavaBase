package main.java.io.github.vaecebyz.test;

import java.util.Scanner;
import main.java.io.github.vaecebyz.domain.*;

public class RectangleTest {
	//�����ж�
	static boolean isWidth = false; 

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		//��
		double length = getUserPrint(scanner); 

		//�Ƿ��ǷǷ�����
		while (length == -1) {
			length = getUserPrint(scanner);
		}

		//��
		double width = getUserPrint(scanner);

		//�Ƿ��ǷǷ�����
		while (width == -1) {
			width = getUserPrint(scanner);
		}

		scanner.close();
		

		Rectangle rectangle = new Rectangle();

		rectangle.setLength(length);

		rectangle.setWidth(width);

		System.out.println("���ε��ܳ��ǣ�" + rectangle.getPerimerter());

		System.out.println("���ε�����ǣ�" + rectangle.getArea());

	}

	private static double getUserPrint(Scanner scanner) {

		//��ʾ
		if (isWidth) {
			System.out.println("��������εĿ�");
		} else {
			System.out.println("��������εĳ���");
		}

		String userPrintString = scanner.nextLine();

		//�ǿ��ж�
		if (!userPrintString.isEmpty()) {
			
			//�˳�
			if(userPrintString.equals("q") || userPrintString.equals("Q")) {
				System.out.println("���˳�");
				System.exit(0);
			}

			//�����쳣�ͷ���ֵ
			try {
					//hasNextFloat
				double userPrintDouble = Double.valueOf(userPrintString);

				if (userPrintDouble > 0) {

					isWidth = true;

					return userPrintDouble;

				} else {
					System.out.println("���������0����");

				}

			} catch (Exception e) {

				System.out.println("���������֡�");

			}

		} else {
			System.out.println("����Ϊ��");

		}

		return -1;

	}

}
