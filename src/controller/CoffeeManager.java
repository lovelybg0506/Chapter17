package controller;

import java.util.ArrayList;

import model.dao.CoffeeDao;
import model.exception.CoffeeException;
import model.vo.Coffee;

public class CoffeeManager {
	private Coffee[] orderArr;
	private ArrayList<Coffee> orderList; // 주문리스트
	private CoffeeDao coDao;
	private int count;

	public CoffeeManager() {
		orderArr = new Coffee[10]; // 커피 주문을 10개 받는 배열
		coDao = new CoffeeDao();
		orderList = coDao.openList();

	}

	public Coffee insertCoffee(Coffee coffee) {
		coffee.setOrderNo(count + 1);
		orderArr[count++] = coffee;
		coffee.setOrderNo(orderList.size() + 1);
		orderList.add(coffee);

		return coffee;
	}

	public Coffee verifyCoffee(int orderNo) throws CoffeeException {

		if (orderNo > orderList.size()) {
			throw new CoffeeException("잘못된 주문 정보입니다.");
		}
		return orderList.get(orderNo);
	}

	public void updateCoffee(int orderNo, Coffee coffee) {
		coffee.setOrderNo(orderNo);
		orderList.set(orderNo, coffee);
	}

	public void deleteCoffee(int idx) {
		orderList.remove(idx);

		if (idx != orderList.size()) {
			for (int i = idx; i < orderList.size(); i++) {
				orderList.get(i).setOrderNo(i + 1);
			}
		}
	}

	public ArrayList<Coffee> getOrderList() {

		return orderList;
	}

	public void setOrderList(ArrayList<Coffee> orderList) {
		this.orderList = orderList;
	}
	
	public void close() {
		coDao.saveList(orderList);
	}

}
