package tr.com.nihatalim.tjk.parser.service;

public interface IParser<T, R> {
    R parse(T param);
}
