package com.fhi.framework.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * 列出目录下(指定深度的)文件和目录 级别表示从指定的目录开始最多搜索层数.层数从1开始 默认层数为1(当前目录下),
 * 如果想搜索所有文件和目录,则指定一个大于255的数字就可以了(XP系统文件名的最大长度为256)
 * 
 */
public class ListSomeLevelDirsFiles implements Iterator<File>, Iterable<File> {
	String dirPath;
	int maxLevel; // 最多层数
	int currentLevel = 1; // 当前层数
	int index;
	List<File> dirs;

	public ListSomeLevelDirsFiles(String dirPath) {
		this(dirPath, 1);
	}

	public ListSomeLevelDirsFiles(String dirPath, int maxLevel) {
		if (dirPath == null || maxLevel <= 0) {
			throw new NullPointerException();
		}
		this.dirPath = dirPath;
		this.maxLevel = maxLevel;
		init();
	}

	public void reset() {
		init();
	}

	public void init() {
		File dir = new File(dirPath);
		if (dir.exists() && dir.isDirectory()) {
			dirs = new Vector<File>();
			dirs.addAll(Arrays.asList(dir.listFiles()));
			index = dirs.size() - 1;
		}
	}

	public boolean hasNext() {
		return dirs != null && !dirs.isEmpty();
	}

	public File next() {
		if (dirs != null && !dirs.isEmpty()) {
			File item = dirs.get(index);
			dirs.remove(index);
			if (item.isDirectory() && currentLevel < maxLevel) {
				dirs.addAll(Arrays.asList(item.listFiles()));
			}
			--index;

			if (index < 0) {
				index = dirs.size() - 1;
				++currentLevel;
			}
			return item;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public Iterator<File> iterator() {
		reset();
		return this;
	}
}
