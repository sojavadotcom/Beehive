package com.sojava.beehive.framework.define;

public class DataFlag {
	public static final short add = 1;
	public static final short modify = 2;
	public static final short delete = 3;
	public static final short file = 4;
	@Deprecated
	public static final short flag5 = 5;
	@Deprecated
	public static final short flag6 = 6;
	@Deprecated
	public static final short flag7 = 7;
	@Deprecated
	public static final short flag8 = 8;
	@Deprecated
	public static final short flag9 = 9;

	public static final short synchronized_add = add + 10;
	public static final short synchronized_modify = modify + 10;
	public static final short synchronized_delete = delete + 10;
	public static final short synchronized_file = file + 10;
	@Deprecated
	public static final short synchronized_flag5 = flag5 + 10;
	@Deprecated
	public static final short synchronized_flag6 = flag6 + 10;
	@Deprecated
	public static final short synchronized_flag7 = flag7 + 10;
	@Deprecated
	public static final short synchronized_flag8 = flag8 + 10;
	@Deprecated
	public static final short synchronized_flag9 = flag9 + 10;

	public static final short synchronize(short no) {
		if (no < 10) return (short) (no+10);
		else return no;
	}
}
