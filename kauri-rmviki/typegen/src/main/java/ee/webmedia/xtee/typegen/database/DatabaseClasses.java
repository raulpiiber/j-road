package ee.webmedia.xtee.typegen.database;

import ee.webmedia.xtee.model.XmlBeansXTeeMetadata;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Tekhov
 */
public class DatabaseClasses {

  private String baseDirectory;
  private String packageName;

  private Map<String, DatabaseClass> classes = new HashMap<String, DatabaseClass>();


  public DatabaseClasses(String baseDirectory, String packageName) {
    this.baseDirectory = baseDirectory;
    this.packageName = packageName;
  }


  public void add(String database, XmlBeansXTeeMetadata metadata) throws IOException {
    DatabaseClass databaseClass = classes.get(database);
    if (databaseClass == null) {
      databaseClass = new DatabaseClass(database, packageName);
      classes.put(database, databaseClass);
    }

    try {
      databaseClass.add(new DatabaseServiceMethod(metadata, baseDirectory));
    } catch (NoDescriptionFoundException e) {
      System.err.println("No XSB found for " + metadata.getOperationName());
    }
  }

  public Map<String, DatabaseClass> getClasses() {
    return classes;
  }
}