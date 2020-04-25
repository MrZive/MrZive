package com.bjsxt.pub;

public class Page {
	/**
	 * 数据总条数
	 */
    private Integer dataCount;
    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 页包含条数
     */
    private Integer pageSize;
    /**
     * 一共页数
     */
    private Integer pageCount;
    /**
     * 数据库查询开始
     */
    private Integer start;
	/**
     * 数据库查询结束
     */
    private Integer count;
    /**
     * 显示的最多页数
     */
    private Integer maxPage;
    /**
     * 开始展现页数
     */
    private Integer beginPage;
    /**
     * 结尾展现页数
     */
    private Integer endPage;
    public Page(Integer pageIndex,Integer pageSize,Integer dataCount){
    	this.pageIndex = pageIndex;
    	this.pageSize = pageSize;
    	this.dataCount = dataCount;
    }
    public Page(Integer pageIndex,Integer pageSize){
    	this.pageIndex = pageIndex;
    	this.pageSize = pageSize;
    }
	public Integer getDataCount() {
		
		return dataCount;
	}
	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		if(this.pageCount == null)
		   this.pageCount = Integer.valueOf(new Double(Math.ceil(new Double(dataCount) / new Double(pageSize))).intValue());
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getStart() {
		return (pageIndex -1 ) * pageSize;
	}
	public Integer getCount() {
		return pageSize;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
		//开始页
		int mid =  new Double(Math.ceil(this.maxPage / 2)).intValue();
		if(this.pageIndex - mid > 0)//前面有足够的页码展示
		{
			this.beginPage = this.pageIndex - mid;
		}else{
			this.beginPage = 1;
		}
		//结束页
		if(this.beginPage + this.maxPage <= getPageCount() ){
			this.endPage = this.beginPage + this.maxPage;
		}else{
			this.endPage = getPageCount();
			if(this.endPage - this.maxPage > 0){
				this.beginPage = this.endPage - this.maxPage;
			}
		}
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getBeginPage() {
		
		if(this.beginPage == null){
			setMaxPage(15);
		}
		
		return beginPage;
	}
	
	public Integer getEndPage() {
		if(this.beginPage == null){
			setMaxPage(15);
		}
		
		return endPage;
	}
	
	
}

