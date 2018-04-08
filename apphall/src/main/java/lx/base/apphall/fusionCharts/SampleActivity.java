package lx.base.apphall.fusionCharts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

public class SampleActivity extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(com.boco.fusioncharts.R.layout.acctivity_sample);

//		WebView test1Wv = (WebView) findViewById(com.boco.fusioncharts.R.id.wv_test1);
//		WebView test2Wv = (WebView) findViewById(com.boco.fusioncharts.R.id.wv_test2);
//
//		// 这条设置不能省略
//		test1Wv.getSettings().setJavaScriptEnabled(true);
//		test2Wv.getSettings().setJavaScriptEnabled(true);
//
//		FusionChartsCreator creator = FusionChartsCreator.getInstance(this);
//
//		FusionChartsConfig config = FusionChartsConfig.createDefaultConfig();
//
//		String html = creator.createChartHtml(FusionChartsType.Line,
//				FusionChartsConfig.createDefaultConfig(), createDatas());
//		test1Wv.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8",
//				null);
//
//		initConifg(config);//自定义样式
//		String html2 = creator.createMultiChartHtml(
//				FusionChartsType.StackedColumn2D, config,
//				createMultiSseriesData());
//		test2Wv.loadDataWithBaseURL("about:blank", html2, "text/html", "utf-8",
//				null);

	}

//	/**
//	 * 个性化定制
//	 */
//	private void initConifg(FusionChartsConfig config) {
//		config.addParams(FusionChartsConfig.ShowLegend, "1");
//		config.addParams("baseFontColor", "FF00FF");// 设置颜色为红色
//	}
//
//	private List<ChartData> createDatas() {
//		List<ChartData> list = new ArrayList<ChartData>();
//		list.add(new ChartData("1号", "15"));
//		list.add(new ChartData("2号", "20"));
//		list.add(new ChartData("3号", "11"));
//		list.add(new ChartData("4号", "8"));
//		list.add(new ChartData("5号", "18"));
//		list.add(new ChartData("6号", "10"));
//
//		return list;
//	}
//
//	private MultiSeriesData createMultiSseriesData() {
//		MultiSeriesData seriesData = new MultiSeriesData();
//
//		Categories categories = new Categories();
//		List<Category> categoryList = new ArrayList<Category>();
//		categoryList.add(new Category("1班"));
//		categoryList.add(new Category("2班"));
//		categoryList.add(new Category("3班"));
//		categoryList.add(new Category("4班"));
//		categoryList.add(new Category("5班"));
//		categories.setCategory(categoryList);
//
//		List<SerieData> dataset = new ArrayList<SerieData>();
//
//		List<ChartData> data1List = new ArrayList<ChartData>();
//		data1List.add(new ChartData("20"));
//		data1List.add(new ChartData("18"));
//		data1List.add(new ChartData("30"));
//		data1List.add(new ChartData("25"));
//		data1List.add(new ChartData("26"));
//
//		dataset.add(new SerieData("男生", data1List));
//
//		List<ChartData> data2List = new ArrayList<ChartData>();
//		data2List.add(new ChartData("24"));
//		data2List.add(new ChartData("28"));
//		data2List.add(new ChartData("26"));
//		data2List.add(new ChartData("22"));
//		data2List.add(new ChartData("30"));
//
//		dataset.add(new SerieData("女生", data2List));
//
//		seriesData.setCategories(categories);
//		seriesData.setDataset(dataset);
//
//		return seriesData;
//	}

}
