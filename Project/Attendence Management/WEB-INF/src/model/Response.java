package model;

public class Response
{
  private static final Integer SC_OK = 200;
  private static final Integer SC_INTERNAL_SERVER_ERROR = 500;

  private static final String RESULT_SUCCESS = "success";
  private static final String RESULT_FAILURE = "failure";

  private Integer statusCode;
  private String result;
  private String reason;
  private Object data;

  public Response()
  {
    this(SC_OK, RESULT_SUCCESS, null, null);

  }

  public Response(Integer statusCode, String result, String reason)
  {
    this(statusCode, result, reason, null);
  }

  public Response(Integer statusCode, String result, String reason, Object data)
  {

    this.statusCode = statusCode;
    this.result = result;
    this.reason = reason;
    this.data = data;
  }

  public Integer getStatusCode()
  {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode)
  {
    this.statusCode = statusCode;
  }

  public String getResult()
  {
    return result;
  }

  public void setResult(String result)
  {
    this.result = result;
  }

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }

  public Object getData()
  {
    return data;
  }

  public void setData(Object data)
  {
    this.data = data;
  }

  public static Response getSuccess()
  {
    return new Response();
  }

  public static Response getSuccess(Object data)
  {
    return new Response(SC_OK, RESULT_SUCCESS, null, data);
  }

  public static Response getFailure(Exception ex)
  {
    return getFailure(ex.getMessage());
  }

  public static Response getFailure(String reason)
  {
    return new Response(SC_INTERNAL_SERVER_ERROR, RESULT_FAILURE, reason);
  }

}
