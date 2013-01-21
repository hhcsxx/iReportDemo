package net.chuangdie.lhb.main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.chuangdie.lhb.bean.Person;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReport
{
	public void viewJasper(String path, String name, String outPath, List<?> list)
	{
		name = parseJrXml(path, name);
		HashMap<String, Object> hm = new HashMap<String, Object>();
		JasperPrint print;
		try
		{
			print = JasperFillManager.fillReport(outPath + name, hm,
					new JRBeanCollectionDataSource(list));
			// JasperPrintManager.printReport(print, false);//
			// 设置为false为直接打印，true会弹出打印预览
			JasperViewer jrview = new JasperViewer(print, false);
			jrview.setPreferredSize(new Dimension(200, 100));
			jrview.setVisible(true);
		} catch (JRException e)
		{
			e.printStackTrace();
		}
	}

	private String parseJrXml(String path, String name)
	{
		// 如果是.jrxml手工编译
		if (name.contains(".jrxml"))
		{
			try
			{
				// 在当前目录编译
				JasperCompileManager.compileReportToFile(path + name);
				name = name.replace(".jrxml", ".jasper");
			} catch (JRException e1)
			{
				e1.printStackTrace();
			}
		}
		System.out.println(name);
		return name;
	}

	public static void main(String[] args)
	{
		List<Person> result = new ArrayList<Person>();
		Person person = new Person();
		person.setName("aaa");
		person.setSex("male");
		person.setIphone(15342679);

		result.add(person);

		person = new Person();
		person.setName("bbb");
		person.setSex("female");
		person.setIphone(1534267900);

		result.add(person);

		JasperReport jr = new JasperReport();
		jr.viewJasper(Const.REPORT_PATH, "person.jrxml", Const.REPORT_PATH, result);
	}
}
