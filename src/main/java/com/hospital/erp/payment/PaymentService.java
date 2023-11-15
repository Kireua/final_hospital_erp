package com.hospital.erp.payment;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.erp.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {
	
	@Autowired
	private PaymentDAO paymentDAO;
	
	//결재추가(기안상신)
	public int paymentInsert(PaymentVO paymentVO)throws Exception {
		
		return paymentDAO.paymentInsert(paymentVO);
	}
	
	//문서전체보기
	public List<PaymentVO> paymentAllList() throws Exception{
		List<PaymentVO> paymentAr = paymentDAO.paymentAllList();
		for(PaymentVO paymentVO : paymentAr) {
			  
			paymentVO.setEpRDate(paymentVO.getEpRDate().substring(0,10));
					  
			}
		return paymentAr;
	}
	
	//로그인 한 사람의 문서 리스트
	public List<PaymentVO> paymentList(MemberVO memberVO) throws Exception{
		List<PaymentVO> paymentAr = paymentDAO.paymentList(memberVO);
		for(PaymentVO paymentVO : paymentAr) {
			  
			paymentVO.setEpRDate(paymentVO.getEpRDate().substring(0,10));
					  
			}
		return paymentAr;
	}

	//문서보기 data
	public PaymentVO paymentData(PaymentVO paymentVO) throws Exception{
		paymentVO = paymentDAO.paymentData(paymentVO);
		
		if (paymentVO.getEpEDate() != null) {
			paymentVO.setEpEDate(paymentVO.getEpEDate().substring(0,10));
		}
		if (paymentVO.getEpSDate() != null) {
			paymentVO.setEpSDate(paymentVO.getEpSDate().substring(0,10));
		}
		//DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//String test = paymentVO.getEpRDate().format(dateTimeFormatter);
		paymentVO.setEpRDate(paymentVO.getEpRDate().substring(0,10));
		
		return paymentVO;
	}
	
}
