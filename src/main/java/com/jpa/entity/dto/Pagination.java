package com.jpa.entity.dto;

import com.jpa.entity.Post;
import com.jpa.repository.PostRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@RequiredArgsConstructor
public class Pagination {


    private Integer page;
    private int rowCount;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;
    private int total;

    public void progress() {
        this.page = page == null ? 0 : page;
        this.pageCount = 5;
        this.endPage = (int) (Math.ceil((page+1) / (double) pageCount) * pageCount);
        this.startPage = endPage - pageCount;
        this.realEnd = (int) Math.ceil(total / (double) rowCount);
        if (realEnd < endPage) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }

}
