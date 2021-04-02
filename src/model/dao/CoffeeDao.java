package model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.vo.Coffee;
public class CoffeeDao implements CoffeeDaoInterface{

	public ArrayList<Coffee> openList() {

		ArrayList<Coffee> list = new ArrayList<Coffee>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("coffee.dat"))) {
			while (in.available() != -1) {
				list.add((Coffee) in.readObject());
			}
		} catch (EOFException e) {
			System.out.println(" 불러오기에 성공하였습니다.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return list;
	}

	public int saveList(ArrayList<Coffee> list) { // 무언가 저장하는 용도의 메소드
		int result = -1; // 선택지( -1을 리턴 )

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("coffee.dat"))) {
			for (Coffee co : list) {
				out.writeObject(co);
			}
			result = 1; // 선택지 ( 1을 리턴 )

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result; // 선택지 ( result를 리턴 )
	}

	public void outStream(ObjectOutputStream out, Coffee co) {
		try {
			out.writeObject(co);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
