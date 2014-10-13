package com.sitech.basd.fusioncharts.vo;

/**
 * 
 * <p>
 * Title: Chart
 * </p>
 * <p>
 * Description: 报表基本属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-13 下午7:54:44
 * 
 */
public class Chart {
	// 报表主标题
	private String caption;
	// 报表子标题
	private String subcaption;
	// X轴名称
	private String xaxisname;
	// Y轴名称
	private String yaxisname;
	// 数字前缀
	private String numberprefix;
	// 数组后缀
	private String numberSuffix;
	//图表字体大小
	private String baseFontSize;
	//图表画布以外的字体样式
	private String outCnvBaseFont;
	//渐变的颜色，如果不需要渐变色，设置为空字符串""
	private String plotGradientColor;
	//显示点的个数
	private String numvisibleplot;
	//是否显示值
	private String showvalues;
	//图两边的空的距离
	private String canvasPadding;
	//显示间距
	private String labelstep;
	//显示方式
	private String labelDisplay;
	//折点半径
	private String anchorRadius;
	//y轴显示的最大值，如果显示百分比可以设置为100
	private String yaxismaxvalue;
	//是否显示x轴的值
	private String showNames;//1显示  0不显示
	//面板背景色
	private String bgColor;
	//画布北京色
	private String canvasBgColor;
	// 画布内部垂直分区线条数，数字
	private String numvdivlines;
	// 是否显示X轴坐标
	private String showLabels;
	
	private String thousandseparator;// 千分位的分隔符
	private String useroundedges;
	
	private String plotspacepercent;//设置柱子的宽度
	
	private String legendborderalpha;
	private String showsum;//是否显示总和
	private String decimals;
	private String showalternatevgridcolor;
	private String formatnumberscale;
	private String linecolor;
	private String linethickness;
	private String alternatehgridalpha;
	private String divlinealpha;
	private String bgalpha;
	private String tooltipbordercolor;
	private String showborder;
	private String tooltipbgcolor;//Background Color for tool tip
	private String anchorborderthickness;//Helps you set border thickness of anchors
	private String anchorbordercolor;
	private String anchorbgcolor;
	private String vdivlineisdashed;
	private String divlinecolor;
	private String canvasbordercolor;
    private String canvasbgalpha;
    private String basefontcolor;
    private String bgcolor;
    //chart单击事件 add by wangjl_cmi_jl 20140804
    private String clickURL;
    
    private String showAboutMenuItem;	//设置右键菜单显示，0隐藏，1显示
    private String aboutMenuItemLabel;	//设置菜单名称
    private String aboutMenuItemLink;	//设置菜单链接地址
    
	// zoon模式下的图标按钮设置
	private String btnResetChartTooltext;
	
	private String btnZoomOutTooltext;
	
	private String btnSwitchToPinModeTooltext;
	
	private String btnSwitchToZoomModeTooltext;
	
	private String btnResetChartTitle;
	
	private String btnZoomOutTitle;
	
	private String btnSwitchtoZoomModeTitle;
	
	private String btnSwitchToPinModeTitle;
	
	
	private String palette;
	private String divintervalhints;
	
	private String drawToolbarButtons;
	
	
	private String numberScaleValue;
	
	private String numberScaleUnit;
	
	private String formatNumberScale;
	
	private String formatNumber;
	/** 默认的缩放单位 */
	private String defaultNumberScale;

	private String drawanchors;
	private String labelpadding;
	private String yaxisvaluespadding;
	
	
	//pie饼图的属性
	
    private String showpercetvalues;// 是否显示百分比

	private String pieRadius;// 半径
	
	private String enableRotation;//
	private String showlabels;//   	
	private String showpercentvalues;//   	
	
	private String showpercentageinlabel;//
	
	private String showlegend;//
	
	private String isTrendZone;//是否显示趋势线
	private String divlineisdashed;//divline是否虚线
	private String alternatehgridcolor;
	private String shadowalpha;
	private String chartrightmargin;
	private String bgangle;
	private String startingAngle;// 起始角度
	private String pieYScale;// 角度
	private String pieSliceDepth;// 图表厚度

	private String manageresize;
	private String origw;
	private String origh;
	private String lowerlimit;
	private String upperlimit;
	private String charttopmargin;
	private String chartbottommargin;
	private String gaugefillmix;
	private String gaugefillratio;
	private String pivotradius;
	private String gaugeouterradius;
	private String gaugeinnerradius;
	private String gaugeoriginx;
	private String gaugeoriginy;
	private String trendvaluedistance;
	private String tickvaluedistance;
	private String managevalueoverlapping;
	private String autoaligntickvalues;
	private String gaugestartangle;
	private String gaugeendangle;
	private String lowerlimitdisplay;
	private String upperlimitdisplay;

	private String slantLabels;	

	private String rotatevalues;
	private String showcolumnshadow;
	private String animation;
	private String valueposition;
	
	private String linealpha;
	
	private String exportenabled;
	public String getExportenabled() {
		return exportenabled;
	}

	public void setExportenabled(String exportenabled) {
		this.exportenabled = exportenabled;
	}

	public String getExportAtClient() {
		return exportAtClient;
	}

	public void setExportAtClient(String exportAtClient) {
		this.exportAtClient = exportAtClient;
	}

	public String getExportDialogMessage() {
		return exportDialogMessage;
	}

	public void setExportDialogMessage(String exportDialogMessage) {
		this.exportDialogMessage = exportDialogMessage;
	}

	public String getExportFormats() {
		return exportFormats;
	}

	public void setExportFormats(String exportFormats) {
		this.exportFormats = exportFormats;
	}

	public String getExportHandler() {
		return exportHandler;
	}

	public void setExportHandler(String exportHandler) {
		this.exportHandler = exportHandler;
	}

	private String exportAtClient;
	private String exportDialogMessage;
	private String exportFormats;
	private String exportHandler;
	
	
	/**
	 *
	 * @return the linealpha
	 */
	public String getLinealpha() {
		return linealpha;
	}

	/**
	 *
	 * @param linealpha the linealpha to set
	 */
	public void setLinealpha(String linealpha) {
		this.linealpha = linealpha;
	}

	/**
	 *
	 * @return the slantLabels
	 */
	public String getSlantLabels() {
		return slantLabels;
	}

	/**
	 *
	 * @param slantLabels the slantLabels to set
	 */
	public void setSlantLabels(String slantLabels) {
		this.slantLabels = slantLabels;
	}

	public String getGaugestartangle() {
		return gaugestartangle;
	}

	public void setGaugestartangle(String gaugestartangle) {
		this.gaugestartangle = gaugestartangle;
	}

	public String getGaugeendangle() {
		return gaugeendangle;
	}

	public void setGaugeendangle(String gaugeendangle) {
		this.gaugeendangle = gaugeendangle;
	}

	public String getIsTrendZone() {
		return isTrendZone;
	}

	public void setIsTrendZone(String isTrendZone) {
		this.isTrendZone = isTrendZone;
	}

	public String getShowlegend() {
		return showlegend;
	}

	public void setShowlegend(String showlegend) {
		this.showlegend = showlegend;
	}

	public String getEnableRotation() {
		return enableRotation;
	}

	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}

	public String getShowpercentageinlabel() {
		return showpercentageinlabel;
	}

	public void setShowpercentageinlabel(String showpercentageinlabel) {
		this.showpercentageinlabel = showpercentageinlabel;
	}

	public String getShowlabels() {
		return showlabels;
	}

	public void setShowlabels(String showlabels) {
		this.showlabels = showlabels;
	}

	public String getShowpercentvalues() {
		return showpercentvalues;
	}

	public String getLinethickness() {
		return linethickness;
	}

	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}

	public void setShowpercentvalues(String showpercentvalues) {
		this.showpercentvalues = showpercentvalues;
	}

	public String getDrawanchors() {
		return drawanchors;
	}

	public void setDrawanchors(String drawanchors) {
		this.drawanchors = drawanchors;
	}

	public String getLabelpadding() {
		return labelpadding;
	}

	public void setLabelpadding(String labelpadding) {
		this.labelpadding = labelpadding;
	}

	public String getYaxisvaluespadding() {
		return yaxisvaluespadding;
	}

	public void setYaxisvaluespadding(String yaxisvaluespadding) {
		this.yaxisvaluespadding = yaxisvaluespadding;
	}

	public String getShowpercetvalues() {
		return showpercetvalues;
	}

	public void setShowpercetvalues(String showpercetvalues) {
		this.showpercetvalues = showpercetvalues;
	}

	public String getPieRadius() {
		return pieRadius;
	}

	public void setPieRadius(String pieRadius) {
		this.pieRadius = pieRadius;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getCanvasBgColor() {
		return canvasBgColor;
	}

	public String getAnchorbgcolor() {
		return anchorbgcolor;
	}

	public String getCanvasbgalpha() {
		return canvasbgalpha;
	}

	public void setCanvasbgalpha(String canvasbgalpha) {
		this.canvasbgalpha = canvasbgalpha;
	}

	public String getBasefontcolor() {
		return basefontcolor;
	}

	public void setBasefontcolor(String basefontcolor) {
		this.basefontcolor = basefontcolor;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getShowborder() {
		return showborder;
	}

	public void setShowborder(String showborder) {
		this.showborder = showborder;
	}

	public String getCanvasbordercolor() {
		return canvasbordercolor;
	}

	public void setCanvasbordercolor(String canvasbordercolor) {
		this.canvasbordercolor = canvasbordercolor;
	}

	public void setAnchorbgcolor(String anchorbgcolor) {
		this.anchorbgcolor = anchorbgcolor;
	}

	public void setCanvasBgColor(String canvasBgColor) {
		this.canvasBgColor = canvasBgColor;
	}

	public String getShowNames() {
		return showNames;
	}

	public String getAnchorbordercolor() {
		return anchorbordercolor;
	}

	public void setAnchorbordercolor(String anchorbordercolor) {
		this.anchorbordercolor = anchorbordercolor;
	}

	public String getVdivlineisdashed() {
		return vdivlineisdashed;
	}

	public void setVdivlineisdashed(String vdivlineisdashed) {
		this.vdivlineisdashed = vdivlineisdashed;
	}

	public void setShowNames(String showNames) {
		this.showNames = showNames;
	}

	public String getYaxismaxvalue() {
		return yaxismaxvalue;
	}

	public String getDivlinecolor() {
		return divlinecolor;
	}

	public void setDivlinecolor(String divlinecolor) {
		this.divlinecolor = divlinecolor;
	}

	public String getTooltipbgcolor() {
		return tooltipbgcolor;
	}

	public void setTooltipbgcolor(String tooltipbgcolor) {
		this.tooltipbgcolor = tooltipbgcolor;
	}

	public void setYaxismaxvalue(String yaxismaxvalue) {
		this.yaxismaxvalue = yaxismaxvalue;
	}

	public String getAnchorRadius() {
		return anchorRadius;
	}

	public void setAnchorRadius(String anchorRadius) {
		this.anchorRadius = anchorRadius;
	}

	public String getLabelDisplay() {
		return labelDisplay;
	}

	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}

	public String getLabelstep() {
		return labelstep;
	}

	public void setLabelstep(String labelstep) {
		this.labelstep = labelstep;
	}

	public String getCanvasPadding() {
		return canvasPadding;
	}

	public void setCanvasPadding(String canvasPadding) {
		this.canvasPadding = canvasPadding;
	}

	public String getShowvalues() {
		return showvalues;
	}

	public void setShowvalues(String showvalues) {
		this.showvalues = showvalues;
	}

	public String getPlotGradientColor() {
		return plotGradientColor;
	}

	public void setPlotGradientColor(String plotGradientColor) {
		this.plotGradientColor = plotGradientColor;
	}

	public String getAnchorborderthickness() {
		return anchorborderthickness;
	}

	public void setAnchorborderthickness(String anchorborderthickness) {
		this.anchorborderthickness = anchorborderthickness;
	}

	public String getNumvisibleplot() {
		return numvisibleplot;
	}

	public void setNumvisibleplot(String numvisibleplot) {
		this.numvisibleplot = numvisibleplot;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubcaption() {
		return subcaption;
	}

	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}

	public String getXaxisname() {
		return xaxisname;
	}

	public void setXaxisname(String xaxisname) {
		this.xaxisname = xaxisname;
	}

	public String getYaxisname() {
		return yaxisname;
	}

	public void setYaxisname(String yaxisname) {
		this.yaxisname = yaxisname;
	}

	public String getNumberprefix() {
		return numberprefix;
	}

	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
	}
	

	public String getBaseFontSize() {
		return baseFontSize;
	}

	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}

	public String getOutCnvBaseFont() {
		return outCnvBaseFont;
	}

	public void setOutCnvBaseFont(String outCnvBaseFont) {
		this.outCnvBaseFont = outCnvBaseFont;
	}

	public String getNumvdivlines() {
		return numvdivlines;
	}

	public void setNumvdivlines(String numvdivlines) {
		this.numvdivlines = numvdivlines;
	}

	public String getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(String showLabels) {
		this.showLabels = showLabels;
	}

	public String getDivintervalhints() {
		return divintervalhints;
	}

	public void setDivintervalhints(String divintervalhints) {
		this.divintervalhints = divintervalhints;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getDrawToolbarButtons() {
		return drawToolbarButtons;
	}

	public void setDrawToolbarButtons(String drawToolbarButtons) {
		this.drawToolbarButtons = drawToolbarButtons;
	}

	public String getBtnResetChartTooltext() {
		return btnResetChartTooltext;
	}

	public void setBtnResetChartTooltext(String btnResetChartTooltext) {
		this.btnResetChartTooltext = btnResetChartTooltext;
	}

	public String getBtnZoomOutTooltext() {
		return btnZoomOutTooltext;
	}

	public void setBtnZoomOutTooltext(String btnZoomOutTooltext) {
		this.btnZoomOutTooltext = btnZoomOutTooltext;
	}

	public String getBtnSwitchToPinModeTooltext() {
		return btnSwitchToPinModeTooltext;
	}

	public void setBtnSwitchToPinModeTooltext(String btnSwitchToPinModeTooltext) {
		this.btnSwitchToPinModeTooltext = btnSwitchToPinModeTooltext;
	}

	public String getBtnSwitchToZoomModeTooltext() {
		return btnSwitchToZoomModeTooltext;
	}

	public void setBtnSwitchToZoomModeTooltext(String btnSwitchToZoomModeTooltext) {
		this.btnSwitchToZoomModeTooltext = btnSwitchToZoomModeTooltext;
	}

	public String getBtnResetChartTitle() {
		return btnResetChartTitle;
	}

	public void setBtnResetChartTitle(String btnResetChartTitle) {
		this.btnResetChartTitle = btnResetChartTitle;
	}

	public String getBtnZoomOutTitle() {
		return btnZoomOutTitle;
	}

	public void setBtnZoomOutTitle(String btnZoomOutTitle) {
		this.btnZoomOutTitle = btnZoomOutTitle;
	}

	public String getBtnSwitchtoZoomModeTitle() {
		return btnSwitchtoZoomModeTitle;
	}

	public void setBtnSwitchtoZoomModeTitle(String btnSwitchtoZoomModeTitle) {
		this.btnSwitchtoZoomModeTitle = btnSwitchtoZoomModeTitle;
	}

	public String getBtnSwitchToPinModeTitle() {
		return btnSwitchToPinModeTitle;
	}

	public void setBtnSwitchToPinModeTitle(String btnSwitchToPinModeTitle) {
		this.btnSwitchToPinModeTitle = btnSwitchToPinModeTitle;
	}

	public String getFormatnumberscale() {
		return formatnumberscale;
	}

	public void setFormatnumberscale(String formatnumberscale) {
		this.formatnumberscale = formatnumberscale;
	}

	public String getNumberScaleValue() {
		return numberScaleValue;
	}

	public void setNumberScaleValue(String numberScaleValue) {
		this.numberScaleValue = numberScaleValue;
	}

	public String getNumberScaleUnit() {
		return numberScaleUnit;
	}

	public void setNumberScaleUnit(String numberScaleUnit) {
		this.numberScaleUnit = numberScaleUnit;
	}

	public String getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}

	public String getFormatNumber() {
		return formatNumber;
	}

	public void setFormatNumber(String formatNumber) {
		this.formatNumber = formatNumber;
	}

	public String getDefaultNumberScale() {
		return defaultNumberScale;
	}

	public void setDefaultNumberScale(String defaultNumberScale) {
		this.defaultNumberScale = defaultNumberScale;
	}

	public String getShowalternatevgridcolor() {
		return showalternatevgridcolor;
	}

	public void setShowalternatevgridcolor(String showalternatevgridcolor) {
		this.showalternatevgridcolor = showalternatevgridcolor;
	}

	public String getLinecolor() {
		return linecolor;
	}

	public void setLinecolor(String linecolor) {
		this.linecolor = linecolor;
	}

	public String getAlternatehgridalpha() {
		return alternatehgridalpha;
	}

	public void setAlternatehgridalpha(String alternatehgridalpha) {
		this.alternatehgridalpha = alternatehgridalpha;
	}

	public String getDivlinealpha() {
		return divlinealpha;
	}

	public void setDivlinealpha(String divlinealpha) {
		this.divlinealpha = divlinealpha;
	}

	public String getBgalpha() {
		return bgalpha;
	}

	public void setBgalpha(String bgalpha) {
		this.bgalpha = bgalpha;
	}

	public String getTooltipbordercolor() {
		return tooltipbordercolor;
	}

	public void setTooltipbordercolor(String tooltipbordercolor) {
		this.tooltipbordercolor = tooltipbordercolor;
	}

	public String getThousandseparator() {
		return thousandseparator;
	}

	public void setThousandseparator(String thousandseparator) {
		this.thousandseparator = thousandseparator;
	}

	public String getShowsum() {
		return showsum;
	}

	public void setShowsum(String showsum) {
		this.showsum = showsum;
	}

	public String getDecimals() {
		return decimals;
	}

	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	public String getUseroundedges() {
		return useroundedges;
	}

	public void setUseroundedges(String useroundedges) {
		this.useroundedges = useroundedges;
	}

	public String getLegendborderalpha() {
		return legendborderalpha;
	}

	public void setLegendborderalpha(String legendborderalpha) {
		this.legendborderalpha = legendborderalpha;
	}

	public String getDivlineisdashed() {
		return divlineisdashed;
	}

	public void setDivlineisdashed(String divlineisdashed) {
		this.divlineisdashed = divlineisdashed;
	}

	public String getAlternatehgridcolor() {
		return alternatehgridcolor;
	}

	public void setAlternatehgridcolor(String alternatehgridcolor) {
		this.alternatehgridcolor = alternatehgridcolor;
	}

	public String getShadowalpha() {
		return shadowalpha;
	}

	public void setShadowalpha(String shadowalpha) {
		this.shadowalpha = shadowalpha;
	}

	public String getChartrightmargin() {
		return chartrightmargin;
	}

	public void setChartrightmargin(String chartrightmargin) {
		this.chartrightmargin = chartrightmargin;
	}

	public String getBgangle() {
		return bgangle;
	}

	public void setBgangle(String bgangle) {
		this.bgangle = bgangle;
	}

	public String getManageresize() {
		return manageresize;
	}

	public void setManageresize(String manageresize) {
		this.manageresize = manageresize;
	}

	public String getOrigw() {
		return origw;
	}

	public void setOrigw(String origw) {
		this.origw = origw;
	}

	public String getOrigh() {
		return origh;
	}

	public void setOrigh(String origh) {
		this.origh = origh;
	}

	public String getLowerlimit() {
		return lowerlimit;
	}

	public void setLowerlimit(String lowerlimit) {
		this.lowerlimit = lowerlimit;
	}

	public String getUpperlimit() {
		return upperlimit;
	}

	public void setUpperlimit(String upperlimit) {
		this.upperlimit = upperlimit;
	}

	public String getCharttopmargin() {
		return charttopmargin;
	}

	public void setCharttopmargin(String charttopmargin) {
		this.charttopmargin = charttopmargin;
	}

	public String getChartbottommargin() {
		return chartbottommargin;
	}

	public void setChartbottommargin(String chartbottommargin) {
		this.chartbottommargin = chartbottommargin;
	}

	public String getGaugefillmix() {
		return gaugefillmix;
	}

	public void setGaugefillmix(String gaugefillmix) {
		this.gaugefillmix = gaugefillmix;
	}

	public String getGaugefillratio() {
		return gaugefillratio;
	}

	public void setGaugefillratio(String gaugefillratio) {
		this.gaugefillratio = gaugefillratio;
	}

	public String getPivotradius() {
		return pivotradius;
	}

	public void setPivotradius(String pivotradius) {
		this.pivotradius = pivotradius;
	}

	public String getGaugeouterradius() {
		return gaugeouterradius;
	}

	public void setGaugeouterradius(String gaugeouterradius) {
		this.gaugeouterradius = gaugeouterradius;
	}

	public String getGaugeinnerradius() {
		return gaugeinnerradius;
	}

	public void setGaugeinnerradius(String gaugeinnerradius) {
		this.gaugeinnerradius = gaugeinnerradius;
	}

	public String getGaugeoriginx() {
		return gaugeoriginx;
	}

	public void setGaugeoriginx(String gaugeoriginx) {
		this.gaugeoriginx = gaugeoriginx;
	}

	public String getGaugeoriginy() {
		return gaugeoriginy;
	}

	public void setGaugeoriginy(String gaugeoriginy) {
		this.gaugeoriginy = gaugeoriginy;
	}

	public String getTrendvaluedistance() {
		return trendvaluedistance;
	}

	public void setTrendvaluedistance(String trendvaluedistance) {
		this.trendvaluedistance = trendvaluedistance;
	}

	public String getTickvaluedistance() {
		return tickvaluedistance;
	}

	public void setTickvaluedistance(String tickvaluedistance) {
		this.tickvaluedistance = tickvaluedistance;
	}

	public String getManagevalueoverlapping() {
		return managevalueoverlapping;
	}

	public void setManagevalueoverlapping(String managevalueoverlapping) {
		this.managevalueoverlapping = managevalueoverlapping;
	}

	public String getAutoaligntickvalues() {
		return autoaligntickvalues;
	}

	public void setAutoaligntickvalues(String autoaligntickvalues) {
		this.autoaligntickvalues = autoaligntickvalues;
	}

	public String getPlotspacepercent() {
		return plotspacepercent;
	}

	public void setPlotspacepercent(String plotspacepercent) {
		this.plotspacepercent = plotspacepercent;
	}

	public String getLowerlimitdisplay() {
		return lowerlimitdisplay;
	}

	public void setLowerlimitdisplay(String lowerlimitdisplay) {
		this.lowerlimitdisplay = lowerlimitdisplay;
	}

	public String getUpperlimitdisplay() {
		return upperlimitdisplay;
	}

	public void setUpperlimitdisplay(String upperlimitdisplay) {
		this.upperlimitdisplay = upperlimitdisplay;
	}

	public String getStartingAngle() {
		return startingAngle;
	}

	public void setStartingAngle(String startingAngle) {
		this.startingAngle = startingAngle;
	}

	public String getPieYScale() {
		return pieYScale;
	}

	public void setPieYScale(String pieYScale) {
		this.pieYScale = pieYScale;
	}

	public String getPieSliceDepth() {
		return pieSliceDepth;
	}

	public void setPieSliceDepth(String pieSliceDepth) {
		this.pieSliceDepth = pieSliceDepth;
	}

	/**
	 *
	 * @return the rotatevalues
	 */
	public String getRotatevalues() {
		return rotatevalues;
	}

	/**
	 *
	 * @param rotatevalues the rotatevalues to set
	 */
	public void setRotatevalues(String rotatevalues) {
		this.rotatevalues = rotatevalues;
	}

	/**
	 *
	 * @return the showcolumnshadow
	 */
	public String getShowcolumnshadow() {
		return showcolumnshadow;
	}

	/**
	 *
	 * @param showcolumnshadow the showcolumnshadow to set
	 */
	public void setShowcolumnshadow(String showcolumnshadow) {
		this.showcolumnshadow = showcolumnshadow;
	}

	/**
	 *
	 * @return the animation
	 */
	public String getAnimation() {
		return animation;
	}

	/**
	 *
	 * @param animation the animation to set
	 */
	public void setAnimation(String animation) {
		this.animation = animation;
	}

	/**
	 *
	 * @return the valueposition
	 */
	public String getValueposition() {
		return valueposition;
	}

	/**
	 *
	 * @param valueposition the valueposition to set
	 */
	public void setValueposition(String valueposition) {
		this.valueposition = valueposition;
	}

	public String getClickURL() {
		return clickURL;
	}

	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}

	public String getShowAboutMenuItem() {
		return showAboutMenuItem;
	}

	public void setShowAboutMenuItem(String showAboutMenuItem) {
		this.showAboutMenuItem = showAboutMenuItem;
	}

	public String getAboutMenuItemLabel() {
		return aboutMenuItemLabel;
	}

	public void setAboutMenuItemLabel(String aboutMenuItemLabel) {
		this.aboutMenuItemLabel = aboutMenuItemLabel;
	}

	public String getAboutMenuItemLink() {
		return aboutMenuItemLink;
	}

	public void setAboutMenuItemLink(String aboutMenuItemLink) {
		this.aboutMenuItemLink = aboutMenuItemLink;
	}
	
	
}
