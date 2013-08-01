/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phprpchessiantest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.phprpc.util.PHPSerializer;

import com.caucho.burlap.io.BurlapInput;
import com.caucho.burlap.io.BurlapOutput;
import com.caucho.hessian.io.Hessian2StreamingInput;
import com.caucho.hessian.io.Hessian2StreamingOutput;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Deserializer;
import com.exadel.flamingo.flex.messaging.amf.io.AMF3Serializer;
import com.googlecode.jsonplugin.JSONUtil;

/**
 * 
 * @author andot
 */
class TestClass implements Serializable {
	private int id;
	private String userName;
	private String password;
	private int age;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the birthday
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}

public class Main {

	public static void runTest(int times, Object data, String info,String filename) 
					throws IllegalAccessException,
								 IllegalArgumentException, InvocationTargetException, IOException,
								 ClassNotFoundException {
		Date start, end;
		long size = 0;
		long stime = 0;
		long dtime = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder categories = new StringBuilder();
		StringBuilder dataset1 = new StringBuilder();
		StringBuilder dataset2 = new StringBuilder();
		StringBuilder dataset3 = new StringBuilder();
		sb.append("<chart caption='"
				+ times
				+ "次"
				+ info
				+ "序列化与反序列化的比较' shownames='1' showvalues='1' decimals='0' formatNumberScale='0' baseFont='Tahama' baseFontSize='12'>");
		categories.append("<categories>");
		dataset1.append("<dataset seriesName='序列化时间'>");
		dataset2.append("<dataset seriesName='反序列化时间'>");
		dataset3.append("<dataset seriesName='空间'>");
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println(times + "次" + info);

		try {
			//准备写缓存大小
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(data);
			oos.close();
			byte[] b = bos.toByteArray();
			size = b.length;
			//准备读缓存大小
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			ObjectInputStream ois = new ObjectInputStream(bis);
			ois.readObject();
			ois.close();
			//计算写缓存速度（即序列化速度）
			start = new Date();
			for (int i = 0; i < times; i++) {
				bos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(bos);
				oos.writeObject(data);
				oos.close();
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			//计算读缓存速度（即反序列化速度）
			start = new Date();
			for (int i = 0; i < times; i++) {
				bis = new ByteArrayInputStream(b);
				ois = new ObjectInputStream(bis);
				ois.readObject();
				ois.close();
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='Java' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("Java - " + "时间：" + stime + "|" + dtime + " 长度："
					+ size);
		} catch (Exception e) {
			System.out.println("Java 不支持该类型");
		}

		try {
			PHPSerializer formator1 = new PHPSerializer();
			byte[] b = formator1.serialize(data);
			size = b.length;
			formator1.unserialize(b);
			start = new Date();
			for (int i = 0; i < times; i++) {
				formator1.serialize(data);
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				formator1.unserialize(b);
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='PHPRPC' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("PHPRPC - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("PHPRPC 不支持该类型");
		}

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			HessianOutput oos = new HessianOutput(bos);
			oos.writeObject(data);
			oos.close();
			byte[] b = bos.toByteArray();
			size = b.length;
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			HessianInput ois = new HessianInput(bis);
			ois.readObject();
			ois.close();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bos = new ByteArrayOutputStream();
				oos = new HessianOutput(bos);
				oos.writeObject(data);
				oos.close();
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bis = new ByteArrayInputStream(b);
				ois = new HessianInput(bis);
				ois.readObject();
				ois.close();
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='Hessian' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("Hessian - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("Hessian 不支持该类型");
		}

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Hessian2StreamingOutput oos = new Hessian2StreamingOutput(bos);
			oos.writeObject(data);
			oos.close();
			byte[] b = bos.toByteArray();
			size = b.length;
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			Hessian2StreamingInput ois = new Hessian2StreamingInput(bis);
			ois.readObject();
			ois.close();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bos = new ByteArrayOutputStream();
				oos = new Hessian2StreamingOutput(bos);
				oos.writeObject(data);
				oos.close();
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bis = new ByteArrayInputStream(b);
				ois = new Hessian2StreamingInput(bis);
				ois.readObject();
				ois.close();
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='Hessian2' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("Hessian2 - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("Hessian2 不支持该类型");
		}

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BurlapOutput oos = new BurlapOutput(bos);
			oos.writeObject(data);
			oos.close();
			byte[] b = bos.toByteArray();
			size = b.length;
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			BurlapInput ois = new BurlapInput(bis);
			ois.readObject();
			ois.close();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bos = new ByteArrayOutputStream();
				oos = new BurlapOutput(bos);
				oos.writeObject(data);
				oos.close();
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bis = new ByteArrayInputStream(b);
				ois = new BurlapInput(bis);
				ois.readObject();
				ois.close();
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='Burlap' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("Burlap - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("Burlap 不支持该类型");
		}

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			AMF3Serializer oos = new AMF3Serializer(bos);
			oos.writeObject(data);
			oos.close();
			byte[] b = bos.toByteArray();
			size = b.length;
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			AMF3Deserializer ois = new AMF3Deserializer(bis);
			ois.readObject();
			ois.close();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bos = new ByteArrayOutputStream();
				oos = new AMF3Serializer(bos);
				oos.writeObject(data);
				oos.close();
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				bis = new ByteArrayInputStream(b);
				ois = new AMF3Deserializer(bis);
				ois.readObject();
				ois.close();
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='AMF3' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("AMF3 - " + "时间：" + stime + "|" + dtime + " 长度："
					+ size);
		} catch (Exception e) {
			System.out.println("AMF3 不支持该类型");
		}

		try {
			JSON json = JSONSerializer.toJSON(data);
			size = json.toString().getBytes("UTF-8").length;
			JSONSerializer.toJava(json);
			start = new Date();
			for (int i = 0; i < times; i++) {
				JSONSerializer.toJSON(data);
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				JSONSerializer.toJava(json);
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='json-lib' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("json-lib - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("json-lib 不支持该类型");
		}

		try {
			String json = JSONUtil.serialize(data);
			size = json.getBytes("UTF-8").length;
			JSONUtil.deserialize(json);
			start = new Date();
			for (int i = 0; i < times; i++) {
				JSONUtil.serialize(data);
			}
			end = new Date();
			stime = end.getTime() - start.getTime();
			start = new Date();
			for (int i = 0; i < times; i++) {
				JSONUtil.deserialize(json);
			}
			end = new Date();
			dtime = end.getTime() - start.getTime();
			categories.append("<category label='jsonplugin' />");
			dataset1.append("<set value='" + stime + "'/>");
			dataset2.append("<set value='" + dtime + "'/>");
			dataset3.append("<set value='" + size + "'/>");
			System.out.println("jsonplugin - " + "时间：" + stime + "|" + dtime
					+ " 长度：" + size);
		} catch (Exception e) {
			System.out.println("jsonplugin 不支持该类型");
		}

		// 以下被注释掉的是 XML 的测试内容，之所以注释掉是因为它太慢了。
		// 如果您有足够耐心，可以取消掉注释，但不要怪我没提醒您。
		/*
		 * try { ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 * XMLEncoder oos = new XMLEncoder(bos); oos.writeObject(data);
		 * oos.close(); byte[] b = bos.toByteArray(); size = b.length;
		 * ByteArrayInputStream bis = new ByteArrayInputStream(b); XMLDecoder
		 * ois = new XMLDecoder(bis); ois.readObject(); ois.close(); start = new
		 * Date(); for (int i = 0; i < times; i++) { bos = new
		 * ByteArrayOutputStream(); oos = new XMLEncoder(bos);
		 * oos.writeObject(data); oos.close(); } end = new Date(); stime =
		 * end.getTime() - start.getTime(); start = new Date(); for (int i = 0;
		 * i < times; i++) { bis = new ByteArrayInputStream(b); ois = new
		 * XMLDecoder(bis); ois.readObject(); ois.close(); } end = new Date();
		 * dtime = end.getTime() - start.getTime();
		 * categories.append("<category label='XML' />");
		 * dataset1.append("<set value='" + stime + "'/>");
		 * dataset2.append("<set value='" + dtime + "'/>");
		 * dataset3.append("<set value='" + size + "'/>");
		 * System.out.println("XML - " + "时间：" + stime + "|" + dtime + " 长度：" +
		 * size); } catch (Exception e) { System.out.println("XML 不支持该类型"); }
		 */
		categories.append("</categories>");
		dataset1.append("</dataset>");
		dataset2.append("</dataset>");
		dataset3.append("</dataset>");
		sb.append(categories);
		sb.append(dataset1);
		sb.append(dataset2);
		sb.append(dataset3);
		sb.append("</chart>");
		FileWriter fw = new FileWriter(filename);
		fw.write(sb.toString());
		fw.close();
		System.out
				.println("--------------------------------------------------------------------------------");
	}

	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException,
			ClassNotFoundException {

		runTest(20000, 12, "对整数12", "RPC framework compare/1.xml");
		runTest(20000, true, "对布尔值true", "RPC framework compare/2.xml");
		runTest(20000, null, "对 null", "RPC framework compare/3.xml");
		runTest(20000, 1.2, "对浮点数1.2", "RPC framework compare/4.xml");
		runTest(20000, 1234567890987654321L, "对UInt64型1234567890987654321",
				"RPC framework compare/5.xml");
		runTest(20000, Double.POSITIVE_INFINITY, "对无穷大",
				"RPC framework compare/6.xml");

		String s = "PHPRPC - perfect high performance remote procedure call";
		runTest(20000, s, "对字符串“" + s + "”", "RPC framework compare/7.xml");

		byte[] ba = new byte[10000];
		for (int i = 0; i < 10000; i++) {
			ba[i] = (byte) (i % 255);
		}
		runTest(2000, ba, "对10000个元素的字节数组", "RPC framework compare/8.xml");

		String[] sa = new String[100];
		for (int i = 0; i < 100; i++) {
			sa[i] = s;
		}
		runTest(2000, sa, "对100个相同元素的字符串数组", "RPC framework compare/9.xml");

		sa = new String[100];
		for (int i = 0; i < 100; i++) {
			sa[i] = s + i;
		}
		runTest(2000, sa, "对100个不同元素的字符串数组", "RPC framework compare/10.xml");

		HashMap h = new HashMap();
		for (int i = 0; i < 100; i++) {
			h.put(s + i, s + (i + 100));
		}
		runTest(2000, h, "对索引不同内容不同具有100个字符串元素和字符串索引的 Hashtable",
				"RPC framework compare/11.xml");

		TestClass tc = new TestClass();
		tc.setId(1);
		tc.setUserName("Ma Bingyao");
		tc.setPassword("PHPRPC");
		tc.setAge(28);
		runTest(200000, tc, "对自定义类型对象", "RPC framework compare/12.xml");
	}
}
