package com.example.refactoring.part1.v4;

public class VideoRental {
	public static void main(String[] args) {
		// Initialize
		Customer leeho = new Customer("이호");
		/* 
		AS-IS: Movie constantin = new Movie("콘스탄틴"); // switch문에 따른 대여료 계산
		TO-BE: Movie constantin = new NewReloeaseMovie("콘스탄틴"); // getCharge()를 재정의한 구현대로 대여료 계산
		*/
//		Movie constantin = new NewReleasePrice("콘스탄틴");
//		Rental rental = new Rental(constantin, 3);
//		leeho.addRental(rental);

		System.out.println(leeho.statement());
	}
}