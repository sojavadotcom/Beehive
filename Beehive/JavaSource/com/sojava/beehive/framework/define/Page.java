package com.sojava.beehive.framework.define;

import java.io.Serializable;

public class Page implements Serializable {
	private static final long serialVersionUID = -8271844574206516513L;

	private int start;
	private int end;
	private int limit;
	private int total;
	private int pages;
	private int currentPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;

	private void initialize() {
		this.start = 0;
		this.end = 0;
		this.limit = 0;
		this.total = 0;
		this.pages = 0;
		this.currentPage = 0;
	}

	public Page() {
		initialize();
	}

	public Page(int start, int end) {
		this.initialize();
		this.start = start < 0 ? 0 : start;
		this.end = end;
		this.limit = this.end-this.start;
	}

	@Deprecated
	public Page(Integer start, Integer limit, Integer total) {
		setStart(start == null || start < 0 ? 0 : start);
		setLimit(limit == null || limit < 0 ? -1 : limit);
		setTotal(total == null || total < 0 ? 0 : total);
	}

	public void calculate() {
		if (total == -1 || limit == -1) {
			setPages(1);
			setCurrentPage(1);
			if (total != -1) setEnd(total);
			else setEnd(start);
		} else {
			setPages(total%limit == 0 ? total/limit : total/limit+1);
			setEnd(start+limit);
			int pageNum = end/limit;
			if (pageNum == 0) setCurrentPage(1);
			else if (pageNum < (double) end/ (double) limit) setCurrentPage(pageNum+1);
			else setCurrentPage(pageNum);
		}
		setFirstPage(currentPage == 1);
		setLastPage(currentPage == pages);
		setHasPreviousPage(currentPage > 1);
		setHasNextPage(currentPage < pages);
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = (start < 0 ? 0 : start);
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = (end > total ? total : end);
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
