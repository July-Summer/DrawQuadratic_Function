package github.July_Summer.DrawQuadratic_Function;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author July_Summer
 * @since 2019,9,28,7
 * @version 1.0.0.0.0.0fuck-version-wdnmd 不用看了 我忘记代入公式了 顶点坐标是错误的
 */
public class DrawTwoHan {

	// 窗口
	public static Display display = Display.getDefault();
	// 二次函数一般式正则 y=ax^2+bx+c
	public static Pattern pattern = Pattern.compile("y=[+-]?[1-9]x\\^2[+[1-9]x][[+-]?[1-9]]");
	// 窗口线程
	public static ExecutorService farmeExecutors = Executors.newFixedThreadPool(10);

	public static void main(String[] str) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("请输入二次函数\n");
			String _quadratic_function = scanner.nextLine();
			// 只支持解析一般式
			if (!pattern.matcher(_quadratic_function).find()) {
				System.out.print("格式错误, 请重新输入!\n");
				continue;
			}
			String[] spilt = _quadratic_function.split("=")[1].split("\\+");
			// 二次项系数
			int _quadratic_coefficient = Integer.parseInt(spilt[0].substring(0, spilt[0].length() - 3));
			// 一次项系数
			int _primary_coefficient = 0;
			if (spilt.length == 3) {
				_primary_coefficient = Integer.parseInt(spilt[1].substring(0, spilt[1].length() - 1));
			} else {
				String number = spilt[1].split("-")[spilt.length - 2];
				_primary_coefficient = Integer.parseInt(number.substring(0, number.length() - 1));
			}
			// 常数项
			int _final_coefficient = spilt.length == 3 ? Integer.parseInt(spilt[2])
					: Integer.parseInt(spilt[1].split("-")[spilt.length - 1]) / -1;
			// 开头方向
			String _starting_direction = _quadratic_coefficient >= 0 ? new String("向上") : new String("向下");
			// 顶点坐标
			String _vertex_location = new String((_quadratic_coefficient / -1) + "," + _final_coefficient);
			// 对称轴
			String _symmetry_axis = new String("直线x=" + (_quadratic_coefficient / -1));
			System.out.print("\n");
			System.out.print("-----------------------------------------\n");
			System.out.print("对称轴: " + _symmetry_axis + "\n");
			System.out.print("顶点坐标: " + _vertex_location + "\n");
			System.out.print("开头方向: " + _starting_direction + "\n");
			System.out.print("二次项系数: " + _quadratic_coefficient + "\n");
			System.out.print("一次项系数: " + _primary_coefficient + "\n");
			System.out.print("常数项: " + _final_coefficient + "\n");
			System.out.print("-----------------------------------------\n");
			System.out.print("\n");
			
			//绘制窗口
			/*initFarme(_starting_direction.equals("向上") ? true :false, 
					new Point(Integer.parseInt(_vertex_location.split(",")[0]), 
							Integer.parseInt(_vertex_location.split(",")[1])));
							*/
		}
	}

	public static void initFarme(boolean _starting_direction, Point _vertex_location) {
		Shell shell = new Shell(display);
			shell.setSize(400, 400);

			shell.addPaintListener(new PaintListener() {
				@Override
				public void paintControl(PaintEvent gc) {
					//绘制直角坐标系
					gc.gc.drawLine(0, 200, 400, 200);
					gc.gc.drawLine(200, 0, 200, 400);
				}
			});
			farmeExecutors.execute(() -> display.syncExec( () ->shell.open()));
			//暂未完工 貌似不能异步打开窗口 *******
	}

}
