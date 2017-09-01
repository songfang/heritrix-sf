package com.vista;

import java.io.File;

import javax.management.InvalidAttributeValueException;

import org.archive.crawler.event.CrawlStatusListener;
import org.archive.crawler.framework.CrawlController;
import org.archive.crawler.framework.exceptions.InitializationException;
import org.archive.crawler.settings.XMLSettingsHandler;

public class crawlthread implements Runnable
{

	String orderFile = "E:\\Documents\\Projects\\eclip luna\\heritrix\\jobs\\new\\order.xml";// order.xml�ļ�·��
	File file = null; // order.xml�ļ�

	CrawlStatusListener listener = null;// ������
	XMLSettingsHandler handler = null; // ��ȡorder.xml�ļ��Ĵ�����
	CrawlController controller = null; // Heritrix�Ŀ�����

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			file = new File(orderFile);
			handler = new XMLSettingsHandler(file);
			handler.initialize();// ��ȡorder.xml�еĸ�������

			controller = new CrawlController();//
			controller.initialize(handler);// �Ӷ�ȡ��order.xml�еĸ�����������ʼ��������

			if (listener != null)
			{
				controller.addCrawlStatusListener(listener);// ��������Ӽ�����
			}
			controller.requestCrawlStart();// ��ʼץȡ

			/*
			 * ���Heritrix��һֱ��������ȴ�
			 */
			while (true)
			{
				if (controller.isRunning() == false)
				{
					break;
				}
				Thread.sleep(1000);
			}

			// ���Heritrix����������ֹͣ
			controller.requestCrawlStop();

		} catch (InvalidAttributeValueException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InitializationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
