package core;

public abstract class Service<T extends Repository> {
    protected T repository;
}
