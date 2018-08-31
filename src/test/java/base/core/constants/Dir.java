package base.core.constants;

/**
 * Created by zhitnikov on 6/6/2017.
 */
public class Dir {

    public static final String PROJECT = System.getProperty("user.dir").replace("\\", "/");
    public static final String RESOURCES = PROJECT + "/src/main/resources";
    public static final String TARGET = PROJECT + "/target";
    public static final String TEST_DATA = PROJECT + "/testData";
    public static final String INPUT = PROJECT + "/input";

    /**
     * sub-folder class for src/main/resources/*
     */
    public static class Resources {

        public static final String TRANSLATIONS = Dir.RESOURCES + "/translations";
        public static final String MCP = Dir.RESOURCES + "/mcp";
        public static final String ADDITIONAL = Dir.RESOURCES + "/additional";

    }
}
