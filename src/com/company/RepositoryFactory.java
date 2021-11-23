package com.company;

public class RepositoryFactory {

    public static Repository getRepository(String repoType) {
        if (repoType == "Database") {
            return new DatabaseRepository();
        } else if (repoType == "InMemory") {
            return new InMemoryRepository();
        }
        return null;
    }
}
