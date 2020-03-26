package com.bjsxt.pub;
/**
 * 考勤对象
 * @author lwf
 *
 */
public class AttendanceReport {
	/**
	 * 打卡时间
	 */
	private String checktime;
	/**
	 * 打卡日期
	 */
	private String judge;
	/**
	 * 人员编号
	 */
	private String userno;
	/**
	 * 打卡机编号
	 */
	private String sn_name;
	/**
	 * 打卡机名称
	 */
	private String alias;
	/**
	 * 人员id
	 */
	private String userid;
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getSn_name() {
		return sn_name;
	}
	public void setSn_name(String sn_name) {
		this.sn_name = sn_name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "AttendanceReport [checktime=" + checktime + ", judge=" + judge + ", userno=" + userno + ", sn_name=" + sn_name + ", alias=" + alias + ", userid=" + userid + "]";
	}
	
}
