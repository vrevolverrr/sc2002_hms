package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;
import model.enums.BloodType;
import model.enums.Gender;
import model.enums.Specialisation;
import model.inventory.InventoryItem;
import model.users.User;
import repository.InventoryRepository;
import repository.UserRepository;
import utils.enums.LoadableType;

/**
 * The {@code Database} class provides utility methods for loading data from CSV files into the database.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class Database {
    /**
     * The list of candidate file names that can be loaded into the database.
     */
    public static final String[] candidateFileNames = {
        "admin.csv", "doctor.csv", "pharmacist.csv", "patient.csv", "inventory.csv"};

    /**
     * Clears all data in the database.
     */
    public static void clear() {

    }

    /**
     * Loads data from the specified file into the database.
     * 
     * @param file the file to load data from.
     * @return {@code true} if the data is successfully loaded; {@code false} otherwise.
     */
    public static boolean load(LoadableFile file) {
        LoadableType loadableType = null;
        List<BaseModel> models = new ArrayList<BaseModel>();

        try (BufferedReader br = new BufferedReader(new FileReader(file.getFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");      

                if (loadableType == null) {
                    loadableType = checkHeader(values);

                    if (loadableType == LoadableType.INVALID) {
                        System.out.println("[ERROR] The file does not have a valid header.");
                        return false;
                    }

                    if (loadableType != LoadableType.INVALID && file.getFileName() == "pharmacist.csv") {
                        loadableType = LoadableType.PHARMACIST;
                    }

                    if (loadableType != LoadableType.INVALID && file.getFileName() == "admin.csv") {
                        loadableType = LoadableType.ADMIN;
                    }

                    System.out.println("[INFO] Detected header for model " + loadableType.getModelClass().getSimpleName());
                    continue;
                }

                BaseModel model = processEntry(loadableType, values);

                if (model != null) {
                    models.add(model);
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] An error occurred while reading the file.");
            return false;
        }

        persistData(models);
        return true;
    }

    /**
     * Get a list of files that can be loaded into the database in the current working directory.
     * @return A list of files that can be loaded into the database.
     */
    public static List<LoadableFile> getLoadableFiles() {
        List<LoadableFile> candidates = new ArrayList<LoadableFile>();

        File cwd = new File(System.getProperty("user.dir"));

        for (String name : candidateFileNames) {
            File file = new File(cwd, name);
            if (file.exists() && file.isFile()) {
                candidates.add(new LoadableFile(file));
            }
        }

        return candidates;  
    }

    /**
     * Get a list of files that can be loaded into the database in the specified directory.
     * @param directory the directory to search for files.
     * @return A list of files that can be loaded into the database.
     */
    private static LoadableType checkHeader(String[] headers) {
        if (headers.length == 0) {
            System.out.println("[ERROR] The file does not have a header.");
            return LoadableType.INVALID;
        }

        for (LoadableType loadableType : LoadableType.values()) {
            if (loadableType == LoadableType.INVALID) {
                continue;
            }

            if (loadableType.getHeaders().length != headers.length) {
                continue;
            }

            if (arrayEquals(loadableType.getHeaders(), headers)) {
                return loadableType;
            }
        }

        return LoadableType.INVALID;
    }

    /**
     * Processes an entry in the CSV file and converts it into a model.
     * 
     * @param loadableType the type of model to process.
     * @param values the values of the entry.
     * @return the model if the entry is successfully processed; {@code null} otherwise.
     */
    private static BaseModel processEntry(LoadableType loadableType, String[] values) {
        if (values.length < loadableType.getHeaders().length) {
            System.out.println("[WARNING] The number of fields in the entry is less than the number of headers. Skipping entry.");
            return null;
        }

        Class<? extends BaseModel> clazz = loadableType.getModelClass();
        String[] headers = loadableType.getHeaders();

        Class<?>[] parameterTypes = new Class<?>[headers.length + 1];
        parameterTypes[0] = String.class;

        Object[] initargs = new Object[values.length + 1];
        // Set ID as null for repository to generate
        initargs[0] = null;

        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals("")) {
                    System.out.println(String.format("[WARNING] The field '%s' is empty. Skipping entry.", headers[i], i));
                    return null;
                }
    
                Field field;
                try {
                    field = clazz.getDeclaredField(headers[i]);
                } catch (NoSuchFieldException e) {
                    Class<?> superclass = clazz.getSuperclass();
    
                    try {
                        field = superclass.getDeclaredField(headers[i]);

                    } catch (NoSuchFieldException e2) {
                        System.out.println(String.format("[WARNING] The field '%s' does not exist in the model. Skipping entry.", headers[i]));
                        return null;
                    }
                }


                Class<?> fieldType = field.getType();
                parameterTypes[i + 1] = fieldType;
    
                Object convertedValue = convertValue(fieldType, values[i]);

                if (convertedValue == null) {
                    System.out.println(String.format("[WARNING] The field '%s' has an invalid value. Skipping entry.", headers[i]));
                    return null;
                }

                initargs[i + 1] = convertedValue;
            }
    
            Constructor<? extends BaseModel> constructor = clazz.getConstructor(parameterTypes);
            BaseModel model = constructor.newInstance(initargs);
            
            return model;

        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Persists the data to the database.
     * 
     * @param models the list of models to persist.
     */
    private static void persistData(List<BaseModel> models) {
        if (models.size() == 0) {
            return;
        }

        BaseModel firstModel = models.get(0);
        System.out.println(String.format("[INFO] Persisting %d entry of %s to the database.", models.size(), firstModel.getClass().getSimpleName()));

        if (firstModel instanceof InventoryItem) {
            InventoryRepository inventoryRepository = new InventoryRepository();
            inventoryRepository.save(models.stream().map(model -> (InventoryItem) model).toList());
        }

        UserRepository userRepository = new UserRepository();
        userRepository.save(models.stream().map(model -> (User) model).toList());
    }

    /**
     * Converts a string value to the specified field type.
     * 
     * @param fieldType the type of the field.
     * @param value the value to convert.
     * @return the converted value if successful; {@code null} otherwise.
     */
    private static Object convertValue(Class<?> fieldType, String value) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);

        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);

        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);

        } else if (fieldType == String.class) {
            return value;

        } else if (fieldType == LocalDate.class) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        } else if (fieldType == Gender.class) {
            if (value.equalsIgnoreCase("m") || value.equalsIgnoreCase("male")) {
                return Gender.MALE;
            } else if (value.equalsIgnoreCase("f") || value.equalsIgnoreCase("female")) {
                return Gender.FEMALE;
            } else {
                return null;
            }

        } else if (fieldType == Specialisation.class) {
            try {
                return Specialisation.valueOf(value.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }

        } else if (fieldType == BloodType.class) {
            switch (value.toLowerCase()) {
                case "a+":
                    return BloodType.A_POSITIVE;
                case "a-":
                    return BloodType.A_NEGATIVE;
                case "b+":
                    return BloodType.B_POSITIVE;
                case "b-":
                    return BloodType.B_NEGATIVE;
                case "o+":
                    return BloodType.O_POSITIVE;
                case "o-":
                    return BloodType.O_NEGATIVE;
                default:
                    return null;
            }
        }

        return null;
    }

    /**
     * A class representing a loadable file.
     */
    public static class LoadableFile {
        private File file;

        private LoadableFile(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        public String getFileName() {
            return file.getName();
        }
    }

    /**
     * Compares two string arrays for equality.
     * 
     * @param a the first string array.
     * @param b the second string array.
     * @return {@code true} if the arrays are equal; {@code false} otherwise.
     */
    private static boolean arrayEquals(String[] a, String[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }

        return true;

    }
}
