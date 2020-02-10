//        Требуется выставить такие настройки, чтобы:
//
//        Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
//        Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
//                Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", независимо от серьезности сообщения печатались в консоль в формате XML (*) и не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".
//                Не упомянутые здесь настройки изменяться не должны.
//
//        (*) В реальных программах мы бы конечно печатали XML не в консоль, а в файл. Но проверяющая система не разрешает создавать файлы на диске, поэтому придется так.
//
//        Подсказки:
//
//        Level есть не только у Logger, но и у Handler.
//                Передача сообщения на обработку родительскому Handler'у регулируется свойством useParentHandlers


import java.util.logging.*;

public class Main {

    public static void main(String[] args) {
        configureLogging();
    }

    private static void configureLogging() {
        final Logger LOGA = Logger.getLogger("org.stepic.java.logging.ClassA");
        final Logger LOGB = Logger.getLogger("org.stepic.java.logging.ClassB");
        final Logger LOGORG = Logger.getLogger("org.stepic.java");

        LOGA.setLevel(Level.ALL);
        LOGB.setLevel(Level.WARNING);

        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        LOGORG.addHandler(handler);
        LOGORG.setUseParentHandlers(false);

        LOGA.log(Level.WARNING, "log class A");


    }

}

class ClassA {

}

class ClassB {

}