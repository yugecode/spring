package cn.code.common.response;

public class ResponseTemplate<T> {
    public static final String SUCESS_CODE = "200";
    public static final String APP_EXCEPTION = "400";
    public static final String UNKNOWN_EXCEPTION = "500";
    public static final String VIEW_MESSAGE_CODE = "200400";
    private Boolean success;
    private String msg;
    private String code;
    private T data;

    public static ResponseTemplate ok() {
        return ok((Object) null);
    }

    public static <T> ResponseTemplate<T> ok(T data) {
        ResponseTemplate<T> response = new ResponseTemplate();
        response.setCode("200");
        response.setMsg("OK");
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> ResponseTemplateBuilder<T> builder() {
        return new ResponseTemplateBuilder();
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseTemplate)) {
            return false;
        } else {
            ResponseTemplate<?> other = (ResponseTemplate) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$success = this.getSuccess();
                    Object other$success = other.getSuccess();
                    if (this$success == null) {
                        if (other$success == null) {
                            break label59;
                        }
                    } else if (this$success.equals(other$success)) {
                        break label59;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseTemplate;
    }

    public String toString() {
        return "ResponseTemplate(success=" + this.getSuccess() + ", msg=" + this.getMsg() + ", code=" + this.getCode() + ", data=" + this.getData() + ")";
    }

    public ResponseTemplate() {
    }

    public ResponseTemplate(final Boolean success, final String msg, final String code, final T data) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static class ResponseTemplateBuilder<T> {
        private Boolean success;
        private String msg;
        private String code;
        private T data;

        ResponseTemplateBuilder() {
        }

        public ResponseTemplateBuilder<T> success(final Boolean success) {
            this.success = success;
            return this;
        }

        public ResponseTemplateBuilder<T> msg(final String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseTemplateBuilder<T> code(final String code) {
            this.code = code;
            return this;
        }

        public ResponseTemplateBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public ResponseTemplate<T> build() {
            return new ResponseTemplate(this.success, this.msg, this.code, this.data);
        }

        public String toString() {
            return "ResponseTemplate.ResponseTemplateBuilder(success=" + this.success + ", msg=" + this.msg + ", code=" + this.code + ", data=" + this.data + ")";
        }
    }
}
