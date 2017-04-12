package com.whayer.wx.coupon.service;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.whayer.wx.base.UnitTestBase;
import com.whayer.wx.common.X;
import com.whayer.wx.common.mvc.Pagination;
import com.whayer.wx.common.mvc.ResponseCondition;
import com.whayer.wx.coupon.vo.Coupon;
import com.whayer.wx.vouchers.service.VoucherService;
import com.whayer.wx.vouchers.vo.Voucher;

public class CouponServiceTest extends UnitTestBase{
	
	public CouponServiceTest() {
		super("classpath:IOC.xml");
	}
	
	@Test
	public void testGetCouponList() {
		CouponService couponService = super.getBean("couponServiceImpl");
		PageInfo<Coupon> pi = couponService.getCouponListByUid("00010102", new Pagination());
		ResponseCondition res = new ResponseCondition();
		res.setIsSuccess(true);
		res.setList(pi.getList());
		res.setPageIndex(pi.getPageNum());
		res.setPageSize(pi.getPageSize());
		res.setTotal(pi.getTotal());
		res.setPages(pi.getPages());
		System.out.println(res.toString());
	}
	
	@Test
	public void testCreateCoupon(){
		CouponService couponService = super.getBean("couponServiceImpl");
		for(int i = 0; i < 10; i++){
			Coupon coupon = new Coupon();
			coupon.setId(X.uuidPure8Bit());
			coupon.setUserId("be9e9b0c-39df-456b-9223-c03fe0f3e77c");
			coupon.setAmount(new BigDecimal(168.88));
			coupon.setEffect(X.TRUE);
			coupon.setExpired(X.FALSE);
			coupon.setCreateDate(null);
			coupon.setUseDate(null);
			coupon.setCreateUserId("be9e9b0c-39df-456b-9223-c03fe0f3e77c");
			
			couponService.saveCoupon(coupon);
		}
	}

	
	@Test
	public void testCreateVoucher(){
		VoucherService voucherService = super.getBean("voucherServiceImpl");
		for(int i = 0; i < 10; i++){
			Voucher voucher = new Voucher();
			voucher.setId(X.uuidPure8Bit());
			voucher.setUserId("be9e9b0c-39df-456b-9223-c03fe0f3e77c");
			voucher.setAmount(new BigDecimal(168.88));
			voucher.setEffect(X.TRUE);
			voucher.setExpired(X.FALSE);
			voucher.setCreateDate(null);
			voucher.setUseDate(null);
			voucher.setCreateUserId("be9e9b0c-39df-456b-9223-c03fe0f3e77c");
			
			voucherService.saveVoucher(voucher);
		}
	}
	
	@Test
	public void testValidate(){
		CouponService couponService = super.getBean("couponServiceImpl");
		ResponseCondition res = new ResponseCondition();
		res.setIsSuccess(true);
		//验证优惠卷
		res = couponService.validate(res, "be9e9b0c-39df-456b-9223-c03fe0f3e77c", "0", "8ZF66dIr");
		//验证代金劵
		res = couponService.validate(res, "be9e9b0c-39df-456b-9223-c03fe0f3e77c", "1", "8ZF66dIr");
		
		System.out.println(res.toString());
		System.out.println(res.toString());
	}
}
