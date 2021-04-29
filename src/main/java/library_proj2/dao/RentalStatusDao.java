package library_proj2.dao;

import java.util.List;

import library_proj2.dto.RentalStatus;
import library_proj2.dto.User;

public interface RentalStatusDao {
	
	// select rentalstatus (리스트 초기화)
	List<RentalStatus> selectRentalStatusByAll();
	
	// 회원화면에서 넘어가기 >> join 부분이라 view에 하기
//	List<RentalStatus> selectRentalStatusByUserNo(User user);

	// 객체넘기기는 view!!
	
	// 대출 반납 트랜잭션에 필요
	int insertRentalStatus(RentalStatus rentalstatus);
	int updateRentalStatus(RentalStatus rentalstatus);
	int deleteRentalStatus(RentalStatus rentalstatus);
	
	// 로그인 할 때 연체일 로드
	int updateRentalStatusLogIn();

	int updateBlackList();
}
