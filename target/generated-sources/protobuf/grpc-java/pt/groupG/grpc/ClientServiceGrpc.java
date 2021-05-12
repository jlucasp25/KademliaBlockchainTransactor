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
    comments = "Source: ClientProto.proto")
public final class ClientServiceGrpc {

  private ClientServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.groupG.grpc.ClientService";

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
    if ((getJoinMethod = ClientServiceGrpc.getJoinMethod) == null) {
      synchronized (ClientServiceGrpc.class) {
        if ((getJoinMethod = ClientServiceGrpc.getJoinMethod) == null) {
          ClientServiceGrpc.getJoinMethod = getJoinMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.JoinMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.JoinMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClientServiceMethodDescriptorSupplier("join"))
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
    if ((getPingMethod = ClientServiceGrpc.getPingMethod) == null) {
      synchronized (ClientServiceGrpc.class) {
        if ((getPingMethod = ClientServiceGrpc.getPingMethod) == null) {
          ClientServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.EmptyMessage, pt.groupG.grpc.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.EmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.BooleanMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClientServiceMethodDescriptorSupplier("ping"))
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
    if ((getFindNodeMethod = ClientServiceGrpc.getFindNodeMethod) == null) {
      synchronized (ClientServiceGrpc.class) {
        if ((getFindNodeMethod = ClientServiceGrpc.getFindNodeMethod) == null) {
          ClientServiceGrpc.getFindNodeMethod = getFindNodeMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeIdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClientServiceMethodDescriptorSupplier("findNode"))
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
    if ((getFindValueMethod = ClientServiceGrpc.getFindValueMethod) == null) {
      synchronized (ClientServiceGrpc.class) {
        if ((getFindValueMethod = ClientServiceGrpc.getFindValueMethod) == null) {
          ClientServiceGrpc.getFindValueMethod = getFindValueMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.NodeDetailsListMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeIdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClientServiceMethodDescriptorSupplier("findValue"))
              .build();
        }
      }
    }
    return getFindValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.BooleanMessage> getStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "store",
      requestType = pt.groupG.grpc.NodeIdMessage.class,
      responseType = pt.groupG.grpc.BooleanMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage,
      pt.groupG.grpc.BooleanMessage> getStoreMethod() {
    io.grpc.MethodDescriptor<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.BooleanMessage> getStoreMethod;
    if ((getStoreMethod = ClientServiceGrpc.getStoreMethod) == null) {
      synchronized (ClientServiceGrpc.class) {
        if ((getStoreMethod = ClientServiceGrpc.getStoreMethod) == null) {
          ClientServiceGrpc.getStoreMethod = getStoreMethod =
              io.grpc.MethodDescriptor.<pt.groupG.grpc.NodeIdMessage, pt.groupG.grpc.BooleanMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "store"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.NodeIdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.groupG.grpc.BooleanMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClientServiceMethodDescriptorSupplier("store"))
              .build();
        }
      }
    }
    return getStoreMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientServiceStub newStub(io.grpc.Channel channel) {
    return new ClientServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ClientServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ClientServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ClientServiceImplBase implements io.grpc.BindableService {

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

    /**
     */
    public void store(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getStoreMethod(), responseObserver);
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
          .addMethod(
            getStoreMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pt.groupG.grpc.NodeIdMessage,
                pt.groupG.grpc.BooleanMessage>(
                  this, METHODID_STORE)))
          .build();
    }
  }

  /**
   */
  public static final class ClientServiceStub extends io.grpc.stub.AbstractStub<ClientServiceStub> {
    private ClientServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientServiceStub(channel, callOptions);
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

    /**
     */
    public void store(pt.groupG.grpc.NodeIdMessage request,
        io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClientServiceBlockingStub extends io.grpc.stub.AbstractStub<ClientServiceBlockingStub> {
    private ClientServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientServiceBlockingStub(channel, callOptions);
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

    /**
     */
    public pt.groupG.grpc.BooleanMessage store(pt.groupG.grpc.NodeIdMessage request) {
      return blockingUnaryCall(
          getChannel(), getStoreMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClientServiceFutureStub extends io.grpc.stub.AbstractStub<ClientServiceFutureStub> {
    private ClientServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientServiceFutureStub(channel, callOptions);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.groupG.grpc.BooleanMessage> store(
        pt.groupG.grpc.NodeIdMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN = 0;
  private static final int METHODID_PING = 1;
  private static final int METHODID_FIND_NODE = 2;
  private static final int METHODID_FIND_VALUE = 3;
  private static final int METHODID_STORE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_STORE:
          serviceImpl.store((pt.groupG.grpc.NodeIdMessage) request,
              (io.grpc.stub.StreamObserver<pt.groupG.grpc.BooleanMessage>) responseObserver);
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

  private static abstract class ClientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.groupG.grpc.ClientProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientService");
    }
  }

  private static final class ClientServiceFileDescriptorSupplier
      extends ClientServiceBaseDescriptorSupplier {
    ClientServiceFileDescriptorSupplier() {}
  }

  private static final class ClientServiceMethodDescriptorSupplier
      extends ClientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientServiceFileDescriptorSupplier())
              .addMethod(getJoinMethod())
              .addMethod(getPingMethod())
              .addMethod(getFindNodeMethod())
              .addMethod(getFindValueMethod())
              .addMethod(getStoreMethod())
              .build();
        }
      }
    }
    return result;
  }
}
