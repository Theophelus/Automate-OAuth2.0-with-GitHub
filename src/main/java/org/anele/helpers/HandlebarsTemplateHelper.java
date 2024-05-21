package org.anele.helpers;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Map;

public class HandlebarsTemplateHelper {

    static LogHelper log = new LogHelper(HandlebarsTemplateHelper.class);
    //define handlebars Object
    static Handlebars handlebars = new Handlebars();

    public static String renderMainTemplates(String path, Map<String, Object> data) {

        try {

            //compile the template from provided path
            Template template = handlebars.compileInline(new String(Files.readAllBytes(Paths.get(path))));
            //apply data the main template
            return template.apply(Context.newBuilder(data).build());

        } catch (Exception e) {
            log.error(
                    "Error occurred while trying to render loader template", e.getMessage());
            return null;
        }
    }

    //render partials templates
    public static String renderPartials(String path, Map<String, Object> statisticsData) {

        try {

            Template template = handlebars.compileInline(new String(Files.readAllBytes(
                    Paths.get(path))));
            return template.apply(Context.newBuilder(statisticsData).build());

        } catch (Exception e) {
            log.error(
                    "Error occurred while trying to load partial template", e.getMessage());
            return null;

        }
    }
}
