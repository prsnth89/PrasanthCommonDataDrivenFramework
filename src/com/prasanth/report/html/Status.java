package com.prasanth.report.html;

public enum Status {
    
    Pass("PASS"),
    Fail("FAIL"),
    Blocked("Blocked"),
    Done("DONE");
    
    String status="";
    
    private Status(String status) {
            this.status=status;
    }
    
    public String toString() {
            return status;
            
    }


}
