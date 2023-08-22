package com.devfox.board.util;

import lombok.Data;

@Data
public class PageNavigator {
    // ページ関連フィールド
    private int countPerPage;		// ページあたりの記事リスト数
    private int pagePerGroup;		// グループあたりのページ数
    private int currentPage;		// 現在のページ
    private int totalRecordsCount;	// 全体記事数
    private int totalPageCount;		// 全ページ数
    private int currentGroup;		// 現在のグループ
    private int startPageGroup;		// 現在のグループのトップページ
    private int endPageGroup;		// 現在のグループの最終ページ
    private int startRecord;		// 全レコードのうち、現在のページの最初の記事の位置(0から始まる)

    /*
     * コンストラクタ
     */
    public PageNavigator(int countPerPage, int pagePerGroup, int currentPage, int totalRecordsCount) {
        this.countPerPage = countPerPage;
        this.pagePerGroup = pagePerGroup;
        this.totalRecordsCount = totalRecordsCount;

        // 全ページ数
        totalPageCount = (totalRecordsCount + countPerPage - 1) / countPerPage;

        // 渡された現在のページが1より小さい場合、現在のページを1ページに指定します。
        if (currentPage < 1) currentPage = 1;
        // 渡された現在のページが最後のページより大きい場合、現在のページを最後のページに指定します。
        if (currentPage > totalPageCount) currentPage = totalPageCount;
        this.currentPage = currentPage;

        // 現在のグループ
        currentGroup = (currentPage - 1) / pagePerGroup;

        // 現在のグループのトップページ
        startPageGroup = currentGroup * pagePerGroup + 1;
        // 現在のグループの最初のページが1より小さい場合は1として処理します。
        startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
        // 現在のグループの最終ページ
        endPageGroup = startPageGroup + pagePerGroup - 1;
        // 現在のグループの最後のページが全体のページ数より小さい場合は、全体のページ数を最後に。
        endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;

        // 全レコードのうち、現在のページの最初の記事の位置
        startRecord = (currentPage - 1) * countPerPage;
    }
}
