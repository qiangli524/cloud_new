package com.sitech.basd.cloud3.domain.charts;

/**
 * 
 * <p>
 * Title: DataPoint
 * </p>
 * <p>
 * Description:对highcharts中series.data中给出的数据节点进行独立配置，具体见options.point，暂时用这几个属性
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Jul 9, 2013
 */
public class DataPoint {
	private long x;// 数据节点的x值。如果省略，x值会根据series.pointStart和series.pointInterval计算出来
	private double y;// 数据节点y值

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

}
