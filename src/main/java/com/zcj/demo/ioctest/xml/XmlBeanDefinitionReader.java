package com.zcj.demo.ioctest.xml;

import com.zcj.demo.ioctest.AbstractBeanDefinitionReader;
import com.zcj.demo.ioctest.BeanDefinition;
import com.zcj.demo.ioctest.BeanReference;
import com.zcj.demo.ioctest.PropertyValue;
import com.zcj.demo.ioctest.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * ����XML�ļ�
 *
 * @author stateis0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  /**
   * ���������������һ����Դ������
   *
   * @param resourceLoader ��Դ������
   */
  public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
    super(resourceLoader);
  }

  public void readerXML(String location) throws Exception {
    // ����һ����Դ������
    ResourceLoader resourceloader = new ResourceLoader();
    // ����Դ�������л�ȡ������
    InputStream inputstream = resourceloader.getResource(location).getInputstream();
    // ��ȡ�ĵ������߹���ʵ��
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // ���������ĵ�������
    DocumentBuilder docBuilder = factory.newDocumentBuilder();
    // �ĵ������߽����� �����ĵ�����
    Document doc = docBuilder.parse(inputstream);
    // ���ݸ������ĵ�������н�������ע�ᵽbean������
    registerBeanDefinitions(doc);
    // �ر���
    inputstream.close();
  }

  /**
   * ���ݸ������ĵ�������н�������ע�ᵽbean������
   *
   * @param doc �ĵ�����
   */
  private void registerBeanDefinitions(Document doc) {
    // ��ȡ�ĵ��ĸ�Ԫ��
    Element root = doc.getDocumentElement();
    // ����Ԫ�صĸ��ڵ㼰���ڵ��µ������ӽڵ㲢��ӽ�ע������
    parseBeanDefinitions(root);
  }

  /**
   * ����Ԫ�صĸ��ڵ㼰���ڵ��µ������ӽڵ㲢��ӽ�ע������
   *
   * @param root XML �ļ����ڵ�
   */
  private void parseBeanDefinitions(Element root) {
    // ��ȡ��Ԫ�ص�������Ԫ��
    NodeList nl = root.getChildNodes();
    // ������Ԫ��
    for (int i = 0; i < nl.getLength(); i++) {
      // ��ȡ��Ԫ�صĸ���λ�õĽڵ�
      Node node = nl.item(i);
      // �����ж�
      if (node instanceof Element) {
        // ǿתΪ������Ԫ��
        Element ele = (Element) node;
        // �����������Ľڵ㣬����name��class��property�� name�� value��ref
        processBeanDefinition(ele);
      }
    }
  }

  /**
   * �����������Ľڵ㣬����name��class��property�� name�� value��ref
   *
   * @param ele XML ����Ԫ��
   */
  private void processBeanDefinition(Element ele) {
    // ��ȡ����Ԫ�ص� name ����
    String name = ele.getAttribute("name");
    // ��ȡ����Ԫ�ص� class ����
    String className = ele.getAttribute("class");
    // ����һ��bean�������
    BeanDefinition beanDefinition = new BeanDefinition();
    // ����bean �������� ȫ�޶�����
    beanDefinition.setClassname(className);
    // �� bean ע�������ļ��еĳ�Ա����
    addPropertyValues(ele, beanDefinition);
    // ��ע������ ���bean���ƺ�bean����
    getRegistry().put(name, beanDefinition);
  }

  /**
   * ��������ļ��е�����Ԫ�ص�bean����ʵ����
   *
   * @param ele Ԫ��
   * @param beandefinition bean���� ����
   */
  private void addPropertyValues(Element ele, BeanDefinition beandefinition) {
    // ��ȡ����Ԫ�ص� property ���Լ���
    NodeList propertyNode = ele.getElementsByTagName("property");
    // ѭ������
    for (int i = 0; i < propertyNode.getLength(); i++) {
      // ��ȡ������ĳ������λ�õĽڵ�
      Node node = propertyNode.item(i);
      // �����ж�
      if (node instanceof Element) {
        // ���ڵ�����ǿתΪ��Ԫ��
        Element propertyEle = (Element) node;
        // Ԫ�ض����ȡ name ����
        String name = propertyEle.getAttribute("name");
        // Ԫ�ض����ȡ value ����ֵ
        String value = propertyEle.getAttribute("value");
        // �ж�value��Ϊ��
        if (value != null && value.length() > 0) {
          // ������� ��bean���塱 ʵ������Ӹó�Ա����
          beandefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
        } else {
          // ���Ϊ�գ����ȡ����ref
          String ref = propertyEle.getAttribute("ref");
          if (ref == null || ref.length() == 0) {
            // �������refΪ�գ����׳��쳣
            throw new IllegalArgumentException(
                "Configuration problem: <property> element for property '"
                    + name + "' must specify a ref or value");
          }
          // �����Ϊ�գ��ⴴ��һ�� ��bean�����á� ʵ�����������Ϊ���ƣ�ʵ����ʱΪ��
          BeanReference beanRef = new BeanReference(name);
          // ������� ��bean���塱 ����ӳ�Ա����
          beandefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanRef));
        }
      }
    }
  }

}
