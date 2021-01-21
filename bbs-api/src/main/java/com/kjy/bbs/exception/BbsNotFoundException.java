package com.kjy.bbs.exception;

public class BbsNotFoundException extends RuntimeException {
    public BbsNotFoundException(Long bbsId) {
        super("BbsNotFoundException id: " + bbsId);
    }
}
