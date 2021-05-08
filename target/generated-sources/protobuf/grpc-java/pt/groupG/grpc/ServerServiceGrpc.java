package pt.groupG.grpc;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: ServerProto.proto")
public final class ServerServiceGrpc {

  private ServerServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.groupG.grpc.ServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage,
      pt.groupG.grpc.JoinMessage> getJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "join",
      requestType = pt.groupG.grpc.EmptyMessage.class,
      responseType = pt.groupG.grpc.JoinMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage,
      pt.groupG.grpc.JoinMessage> getJoinMethod() {
    io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.JoinMessage> getJoinMethod;
    if ((getJoinMethod = ServerServiceGrpc.getJoinMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getJoinMethod = ServerServiceGrpc.getJoinMethod) == null) {
          ServerServiceGrpc.getJoinMethod = getJoinMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.JoinMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.JoinMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("join"))
              .build();
        }
      }
    }
    return getJoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage,
      pt.groupG.grpc.BooleanMessage> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = pt.groupG.grpc.EmptyMessage.class,
      responseType = pt.groupG.grpc.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage,
      pt.groupG.grpc.BooleanMessage> getPingMethod() {
    io.grpc.MethodDescriptor<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.BooleanMessage> getPingMethod;
    if ((getPingMethod = ServerServiceGrpc.getPingMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getPingMethod = ServerServiceGrpc.getPingMethod) == null) {
          ServerServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.BooleanMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.NodeDetailsListMessage> getFindNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findNode",
      requestType = pt.groupG.grpc.NodeIdMessage.class,
      responseType = pt.groupG.grpc.NodeDetailsListMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.NodeDetailsListMessage> getFindNodeMethod() {
    io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage> getFindNodeMethod;
    if ((getFindNodeMethod = ServerServiceGrpc.getFindNodeMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getFindNodeMethod = ServerServiceGrpc.getFindNodeMethod) == null) {
          ServerServiceGrpc.getFindNodeMethod = getFindNodeMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeIdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("findNode"))
              .build();
        }
      }
    }
    return getFindNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.NodeDetailsListMessage> getFindValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findValue",
      requestType = pt.groupG.grpc.NodeIdMessage.class,
      responseType = pt.groupG.grpc.NodeDetailsListMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.NodeDetailsListMessage> getFindValueMethod() {
    io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage> getFindValueMethod;
    if ((getFindValueMethod = ServerServiceGrpc.getFindValueMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getFindValueMethod = ServerServiceGrpc.getFindValueMethod) == null) {
          ServerServiceGrpc.getFindValueMethod = getFindValueMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeIdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("findValue"))
              .build();
        }
      }
    }
    return getFindValueMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerServiceStub newStub(io.grpc.Channel channel) {
    return new ServerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ServerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ServerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ServerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void join(pt.groupG.grpc.EmptyMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.JoinMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
    }

    /**
     */
    public void ping(pt.groupG.grpc.EmptyMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void findNode(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getFindNodeMethod(), responseObserver);
    }

    /**
     */
    public void findValue(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getFindValueMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pt.groupG.grpc.EmptyMessage,
                pt.groupG.grpc.JoinMessage>(
                  this, METHODID_JOIN)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pt.groupG.grpc.EmptyMessage,
                pt.groupG.grpc.BooleanMessage>(
                  this, METHODID_PING)))
          .addMethod(
            getFindNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pt.groupG.grpc.NodeIdMessage,
                pt.groupG.grpc.NodeDetailsListMessage>(
                  this, METHODID_FIND_NODE)))
          .addMethod(
            getFindValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pt.groupG.grpc.NodeIdMessage,
                pt.groupG.grpc.NodeDetailsListMessage>(
                  this, METHODID_FIND_VALUE)))
          .build();
    }
  }

  /**
   */
  public static final class ServerServiceStub extends io.grpc.stub.AbstractStub<ServerServiceStub> {
    private ServerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerServiceStub(channel, callOptions);
    }

    /**
     */
    public void join(pt.groupG.grpc.EmptyMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.JoinMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(pt.groupG.grpc.EmptyMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findNode(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findValue(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindValueMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ServerServiceBlockingStub extends io.grpc.stub.AbstractStub<ServerServiceBlockingStub> {
    private ServerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pt.groupG.grpc.JoinMessage join(pt.groupG.grpc.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.groupG.grpc.BooleanMessage ping(pt.groupG.grpc.EmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.groupG.grpc.NodeDetailsListMessage findNode(pt.groupG.grpc.NodeIdMessage request) {
      return blockingUnaryCall(
          getChannel(), getFindNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.groupG.grpc.NodeDetailsListMessage findValue(pt.groupG.grpc.NodeIdMessage request) {
      return blockingUnaryCall(
          getChannel(), getFindValueMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ServerServiceFutureStub extends io.grpc.stub.AbstractStub<ServerServiceFutureStub> {
    private ServerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.groupG.grpc.JoinMessage> join(
        pt.groupG.grpc.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.groupG.grpc.BooleanMessage> ping(
        pt.groupG.grpc.EmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.groupG.grpc.NodeDetailsListMessage> findNode(
        pt.groupG.grpc.NodeIdMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getFindNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.groupG.grpc.NodeDetailsListMessage> findValue(
        pt.groupG.grpc.NodeIdMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getFindValueMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN = 0;
  private static final int METHODID_PING = 1;
  private static final int METHODID_FIND_NODE = 2;
  private static final int METHODID_FIND_VALUE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ServerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN:
          serviceImpl.join((pt.groupG.grpc.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<pt.groupG.grpc.JoinMessage>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((pt.groupG.grpc.EmptyMessage) request,
              (io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage>) responseObserver);
          break;
        case METHODID_FIND_NODE:
          serviceImpl.findNode((pt.groupG.grpc.NodeIdMessage) request,
              (io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage>) responseObserver);
          break;
        case METHODID_FIND_VALUE:
          serviceImpl.findValue((pt.groupG.grpc.NodeIdMessage) request,
              (io.grpc.stub.StreamObserver<pt.groupG.grpc.NodeDetailsListMessage>) responseObserver);
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

  private static abstract class ServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.groupG.grpc.ServerProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ServerService");
    }
  }

  private static final class ServerServiceFileDescriptorSupplier
      extends ServerServiceBaseDescriptorSupplier {
    ServerServiceFileDescriptorSupplier() {}
  }

  private static final class ServerServiceMethodDescriptorSupplier
      extends ServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServerServiceFileDescriptorSupplier())
              .addMethod(getJoinMethod())
              .addMethod(getPingMethod())
              .addMethod(getFindNodeMethod())
              .addMethod(getFindValueMethod())
              .build();
        }
      }
    }
    return result;
  }
}
