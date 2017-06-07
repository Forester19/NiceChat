package Tamplate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Владислав on 28.01.2017.
 */
public class PageGenerator {
    private static PageGenerator pageGenerator;
    private Configuration cnf;

   private PageGenerator() {
         cnf = new Configuration();
    }

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }
    public String getPage(String filename, Map<String,Object> data){
        Writer s = new StringWriter();
        try {
            Template template = cnf.getTemplate(filename);
            template.process(data,s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
