package com.goldendays.config;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class WebServiceUtil {

	private static final String LIB_GOLDENDAYSSERVICE_0_0_1_SNAPSHOT_JAR = "lib/goldendaysservice-0.0.1-SNAPSHOT.jar";
	public static final String COM_GOLDENDAYS_CONFIG_PROPERTIES_WEB_SERVICE_ADDRESS = "META-INF/webserviceRegistry";
	public static final String COM_GOLDENDAYS_WEBSERVICE_CONTRACT = "com/goldendays/webservice/contract";
	public static final String COM_GOLDENDAYS_WEBSERVICE_IMP = "com/goldendays/webservice/implementation";

	public static void m(int i) {
		String jarPath = parentPath(i) + LIB_GOLDENDAYSSERVICE_0_0_1_SNAPSHOT_JAR;
		try {
			List<String> classList = getClassFromJar(jarPath, ".class", COM_GOLDENDAYS_WEBSERVICE_IMP);
			List<String> propertiesList = getClassFromJar(jarPath, ".properties",
					COM_GOLDENDAYS_CONFIG_PROPERTIES_WEB_SERVICE_ADDRESS);
			String propertiesFileDirecory = propertiesList.get(0) + ".properties";

			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileDirecory);
			// String theString = IOUtils.toString(is, "UTF-8");

			Properties props = new Properties();
			props.load(is);

			for (String str : classList) {
				if (props.getProperty(str) != null) {
					// Create class of type Base.
					Class<?> myClass = Class.forName(str);
					// Create constructor call with argument types.
					Constructor<?> ctr = myClass.getConstructor();
					// Finally create object of type Base and pass data to
					// constructor.
					Object object = ctr.newInstance();
					registerWebService(object, props.getProperty(str));
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static  void registerWebService(Object obj, String str) {
		System.out.println("Web Service Publishing");
		// String address = str;
		// Endpoint.publish(address, obj);
		
		
		
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(obj.getClass().getInterfaces()[0]);
		svrFactory.setAddress(str);
		svrFactory.setServiceBean(obj); 
		svrFactory.create();
	}

	private static String parentPath(int parentIndex) {
		int i = 0;
		String directory = "/";
		boolean state = true;
		String[] arr = classpathOftheProject();
		do {
			if (i == (arr.length - (1 + parentIndex))) {
				state = false;
			}
			directory += arr[i] + "/";
			++i;
		} while (state);
		return directory;
	}

	private static String[] classpathOftheProject() {
		return Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/");
	}

	private static List<String> getClassFromJar(String jarPath, String innerFileType, String path)
			throws ClassNotFoundException {

		String fileName = "";
		JarFile jarFile = null;
		List<String> listOfFile = new ArrayList<String>();
		Enumeration<JarEntry> e;
		try {
			jarFile = new JarFile(jarPath);
			e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + jarPath + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				// System.out.println(je.getName());

				if (je.isDirectory() || !je.getName().endsWith(innerFileType)) {
					continue;
				}
				if (!je.getName().contains(path)) {
					continue;
				}
				if (".class".equals(innerFileType)) {
					// -6 because of .class
					fileName = je.getName().substring(0, je.getName().length() - 6);
					fileName = fileName.replace('/', '.');
				} else if (".properties".equals(innerFileType)) {
					// -11 because of .properties
					fileName = je.getName().substring(0, je.getName().length() - 11);
				}
				listOfFile.add(fileName);
				System.out.println(fileName);
				// Class c = cl.loadClass(className);
			}

		} catch (IOException exc) {
			exc.printStackTrace();
		} finally {
			if (jarFile != null)
				try {
					jarFile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		return listOfFile;
	}
}
