package SmartOffice.service3;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *definition for service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service3.proto")
public final class Service3Grpc {

  private Service3Grpc() {}

  public static final String SERVICE_NAME = "service3.Service3";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SmartOffice.service3.CheckInRequest,
      SmartOffice.service3.CheckInResponse> getCheckInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkIn",
      requestType = SmartOffice.service3.CheckInRequest.class,
      responseType = SmartOffice.service3.CheckInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SmartOffice.service3.CheckInRequest,
      SmartOffice.service3.CheckInResponse> getCheckInMethod() {
    io.grpc.MethodDescriptor<SmartOffice.service3.CheckInRequest, SmartOffice.service3.CheckInResponse> getCheckInMethod;
    if ((getCheckInMethod = Service3Grpc.getCheckInMethod) == null) {
      synchronized (Service3Grpc.class) {
        if ((getCheckInMethod = Service3Grpc.getCheckInMethod) == null) {
          Service3Grpc.getCheckInMethod = getCheckInMethod = 
              io.grpc.MethodDescriptor.<SmartOffice.service3.CheckInRequest, SmartOffice.service3.CheckInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service3.Service3", "checkIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service3.CheckInRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service3.CheckInResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service3MethodDescriptorSupplier("checkIn"))
                  .build();
          }
        }
     }
     return getCheckInMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SmartOffice.service3.CheckOutRequest,
      SmartOffice.service3.CheckOutResponse> getCheckOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkOut",
      requestType = SmartOffice.service3.CheckOutRequest.class,
      responseType = SmartOffice.service3.CheckOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SmartOffice.service3.CheckOutRequest,
      SmartOffice.service3.CheckOutResponse> getCheckOutMethod() {
    io.grpc.MethodDescriptor<SmartOffice.service3.CheckOutRequest, SmartOffice.service3.CheckOutResponse> getCheckOutMethod;
    if ((getCheckOutMethod = Service3Grpc.getCheckOutMethod) == null) {
      synchronized (Service3Grpc.class) {
        if ((getCheckOutMethod = Service3Grpc.getCheckOutMethod) == null) {
          Service3Grpc.getCheckOutMethod = getCheckOutMethod = 
              io.grpc.MethodDescriptor.<SmartOffice.service3.CheckOutRequest, SmartOffice.service3.CheckOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service3.Service3", "checkOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service3.CheckOutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service3.CheckOutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service3MethodDescriptorSupplier("checkOut"))
                  .build();
          }
        }
     }
     return getCheckOutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Service3Stub newStub(io.grpc.Channel channel) {
    return new Service3Stub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Service3BlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Service3BlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Service3FutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Service3FutureStub(channel);
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static abstract class Service3ImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkIn(SmartOffice.service3.CheckInRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service3.CheckInResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckInMethod(), responseObserver);
    }

    /**
     */
    public void checkOut(SmartOffice.service3.CheckOutRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service3.CheckOutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckOutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SmartOffice.service3.CheckInRequest,
                SmartOffice.service3.CheckInResponse>(
                  this, METHODID_CHECK_IN)))
          .addMethod(
            getCheckOutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SmartOffice.service3.CheckOutRequest,
                SmartOffice.service3.CheckOutResponse>(
                  this, METHODID_CHECK_OUT)))
          .build();
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service3Stub extends io.grpc.stub.AbstractStub<Service3Stub> {
    private Service3Stub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service3Stub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service3Stub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service3Stub(channel, callOptions);
    }

    /**
     */
    public void checkIn(SmartOffice.service3.CheckInRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service3.CheckInResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckInMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkOut(SmartOffice.service3.CheckOutRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service3.CheckOutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service3BlockingStub extends io.grpc.stub.AbstractStub<Service3BlockingStub> {
    private Service3BlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service3BlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service3BlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service3BlockingStub(channel, callOptions);
    }

    /**
     */
    public SmartOffice.service3.CheckInResponse checkIn(SmartOffice.service3.CheckInRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckInMethod(), getCallOptions(), request);
    }

    /**
     */
    public SmartOffice.service3.CheckOutResponse checkOut(SmartOffice.service3.CheckOutRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckOutMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service3FutureStub extends io.grpc.stub.AbstractStub<Service3FutureStub> {
    private Service3FutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service3FutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service3FutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service3FutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SmartOffice.service3.CheckInResponse> checkIn(
        SmartOffice.service3.CheckInRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckInMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SmartOffice.service3.CheckOutResponse> checkOut(
        SmartOffice.service3.CheckOutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_IN = 0;
  private static final int METHODID_CHECK_OUT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Service3ImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Service3ImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_IN:
          serviceImpl.checkIn((SmartOffice.service3.CheckInRequest) request,
              (io.grpc.stub.StreamObserver<SmartOffice.service3.CheckInResponse>) responseObserver);
          break;
        case METHODID_CHECK_OUT:
          serviceImpl.checkOut((SmartOffice.service3.CheckOutRequest) request,
              (io.grpc.stub.StreamObserver<SmartOffice.service3.CheckOutResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Service3BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Service3BaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SmartOffice.service3.Service3Impl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service3");
    }
  }

  private static final class Service3FileDescriptorSupplier
      extends Service3BaseDescriptorSupplier {
    Service3FileDescriptorSupplier() {}
  }

  private static final class Service3MethodDescriptorSupplier
      extends Service3BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Service3MethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (Service3Grpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Service3FileDescriptorSupplier())
              .addMethod(getCheckInMethod())
              .addMethod(getCheckOutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
