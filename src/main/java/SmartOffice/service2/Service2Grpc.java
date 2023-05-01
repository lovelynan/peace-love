package SmartOffice.service2;

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
    comments = "Source: service2.proto")
public final class Service2Grpc {

  private Service2Grpc() {}

  public static final String SERVICE_NAME = "service2.Service2";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SmartOffice.service2.RoomRequest,
      SmartOffice.service2.RoomList> getGetAvailableRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAvailableRooms",
      requestType = SmartOffice.service2.RoomRequest.class,
      responseType = SmartOffice.service2.RoomList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SmartOffice.service2.RoomRequest,
      SmartOffice.service2.RoomList> getGetAvailableRoomsMethod() {
    io.grpc.MethodDescriptor<SmartOffice.service2.RoomRequest, SmartOffice.service2.RoomList> getGetAvailableRoomsMethod;
    if ((getGetAvailableRoomsMethod = Service2Grpc.getGetAvailableRoomsMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getGetAvailableRoomsMethod = Service2Grpc.getGetAvailableRoomsMethod) == null) {
          Service2Grpc.getGetAvailableRoomsMethod = getGetAvailableRoomsMethod = 
              io.grpc.MethodDescriptor.<SmartOffice.service2.RoomRequest, SmartOffice.service2.RoomList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service2.Service2", "GetAvailableRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.RoomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.RoomList.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("GetAvailableRooms"))
                  .build();
          }
        }
     }
     return getGetAvailableRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SmartOffice.service2.BookRequest,
      SmartOffice.service2.BookResponse> getBookRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BookRoom",
      requestType = SmartOffice.service2.BookRequest.class,
      responseType = SmartOffice.service2.BookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<SmartOffice.service2.BookRequest,
      SmartOffice.service2.BookResponse> getBookRoomMethod() {
    io.grpc.MethodDescriptor<SmartOffice.service2.BookRequest, SmartOffice.service2.BookResponse> getBookRoomMethod;
    if ((getBookRoomMethod = Service2Grpc.getBookRoomMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getBookRoomMethod = Service2Grpc.getBookRoomMethod) == null) {
          Service2Grpc.getBookRoomMethod = getBookRoomMethod = 
              io.grpc.MethodDescriptor.<SmartOffice.service2.BookRequest, SmartOffice.service2.BookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service2.Service2", "BookRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.BookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.BookResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("BookRoom"))
                  .build();
          }
        }
     }
     return getBookRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SmartOffice.service2.CancelRequest,
      SmartOffice.service2.CancelResponse> getCancelRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelRoom",
      requestType = SmartOffice.service2.CancelRequest.class,
      responseType = SmartOffice.service2.CancelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SmartOffice.service2.CancelRequest,
      SmartOffice.service2.CancelResponse> getCancelRoomMethod() {
    io.grpc.MethodDescriptor<SmartOffice.service2.CancelRequest, SmartOffice.service2.CancelResponse> getCancelRoomMethod;
    if ((getCancelRoomMethod = Service2Grpc.getCancelRoomMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getCancelRoomMethod = Service2Grpc.getCancelRoomMethod) == null) {
          Service2Grpc.getCancelRoomMethod = getCancelRoomMethod = 
              io.grpc.MethodDescriptor.<SmartOffice.service2.CancelRequest, SmartOffice.service2.CancelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service2.Service2", "CancelRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.CancelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SmartOffice.service2.CancelResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("CancelRoom"))
                  .build();
          }
        }
     }
     return getCancelRoomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Service2Stub newStub(io.grpc.Channel channel) {
    return new Service2Stub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Service2BlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Service2BlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Service2FutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Service2FutureStub(channel);
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static abstract class Service2ImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Get list of available rooms
     * </pre>
     */
    public void getAvailableRooms(SmartOffice.service2.RoomRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service2.RoomList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAvailableRoomsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC to book a specific room
     * </pre>
     */
    public void bookRoom(SmartOffice.service2.BookRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service2.BookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBookRoomMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC to cancel booking of a specific room
     * </pre>
     */
    public io.grpc.stub.StreamObserver<SmartOffice.service2.CancelRequest> cancelRoom(
        io.grpc.stub.StreamObserver<SmartOffice.service2.CancelResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCancelRoomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAvailableRoomsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SmartOffice.service2.RoomRequest,
                SmartOffice.service2.RoomList>(
                  this, METHODID_GET_AVAILABLE_ROOMS)))
          .addMethod(
            getBookRoomMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                SmartOffice.service2.BookRequest,
                SmartOffice.service2.BookResponse>(
                  this, METHODID_BOOK_ROOM)))
          .addMethod(
            getCancelRoomMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SmartOffice.service2.CancelRequest,
                SmartOffice.service2.CancelResponse>(
                  this, METHODID_CANCEL_ROOM)))
          .build();
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service2Stub extends io.grpc.stub.AbstractStub<Service2Stub> {
    private Service2Stub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2Stub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2Stub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Get list of available rooms
     * </pre>
     */
    public void getAvailableRooms(SmartOffice.service2.RoomRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service2.RoomList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAvailableRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC to book a specific room
     * </pre>
     */
    public void bookRoom(SmartOffice.service2.BookRequest request,
        io.grpc.stub.StreamObserver<SmartOffice.service2.BookResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getBookRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC to cancel booking of a specific room
     * </pre>
     */
    public io.grpc.stub.StreamObserver<SmartOffice.service2.CancelRequest> cancelRoom(
        io.grpc.stub.StreamObserver<SmartOffice.service2.CancelResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCancelRoomMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service2BlockingStub extends io.grpc.stub.AbstractStub<Service2BlockingStub> {
    private Service2BlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2BlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2BlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2BlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get list of available rooms
     * </pre>
     */
    public SmartOffice.service2.RoomList getAvailableRooms(SmartOffice.service2.RoomRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAvailableRoomsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server streaming RPC to book a specific room
     * </pre>
     */
    public java.util.Iterator<SmartOffice.service2.BookResponse> bookRoom(
        SmartOffice.service2.BookRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getBookRoomMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *definition for service
   * </pre>
   */
  public static final class Service2FutureStub extends io.grpc.stub.AbstractStub<Service2FutureStub> {
    private Service2FutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2FutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2FutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2FutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get list of available rooms
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<SmartOffice.service2.RoomList> getAvailableRooms(
        SmartOffice.service2.RoomRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAvailableRoomsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_AVAILABLE_ROOMS = 0;
  private static final int METHODID_BOOK_ROOM = 1;
  private static final int METHODID_CANCEL_ROOM = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Service2ImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Service2ImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_AVAILABLE_ROOMS:
          serviceImpl.getAvailableRooms((SmartOffice.service2.RoomRequest) request,
              (io.grpc.stub.StreamObserver<SmartOffice.service2.RoomList>) responseObserver);
          break;
        case METHODID_BOOK_ROOM:
          serviceImpl.bookRoom((SmartOffice.service2.BookRequest) request,
              (io.grpc.stub.StreamObserver<SmartOffice.service2.BookResponse>) responseObserver);
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
        case METHODID_CANCEL_ROOM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.cancelRoom(
              (io.grpc.stub.StreamObserver<SmartOffice.service2.CancelResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Service2BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Service2BaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SmartOffice.service2.Service2Impl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service2");
    }
  }

  private static final class Service2FileDescriptorSupplier
      extends Service2BaseDescriptorSupplier {
    Service2FileDescriptorSupplier() {}
  }

  private static final class Service2MethodDescriptorSupplier
      extends Service2BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Service2MethodDescriptorSupplier(String methodName) {
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
      synchronized (Service2Grpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Service2FileDescriptorSupplier())
              .addMethod(getGetAvailableRoomsMethod())
              .addMethod(getBookRoomMethod())
              .addMethod(getCancelRoomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
