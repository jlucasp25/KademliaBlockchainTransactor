// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ClientProto.proto

package pt.groupG.grpc;

public final class ClientProtos {
  private ClientProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pt_groupG_grpc_JoinMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pt_groupG_grpc_JoinMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pt_groupG_grpc_BooleanMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pt_groupG_grpc_BooleanMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pt_groupG_grpc_EmptyMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pt_groupG_grpc_EmptyMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021ClientProto.proto\022\016pt.groupG.grpc\",\n\013J" +
      "oinMessage\022\017\n\007address\030\001 \001(\t\022\014\n\004port\030\002 \001(" +
      "\005\"\037\n\016BooleanMessage\022\r\n\005value\030\001 \001(\010\"\016\n\014Em" +
      "ptyMessage2\230\001\n\rClientService\022A\n\004join\022\034.p" +
      "t.groupG.grpc.EmptyMessage\032\033.pt.groupG.g" +
      "rpc.JoinMessage\022D\n\004ping\022\034.pt.groupG.grpc" +
      ".EmptyMessage\032\036.pt.groupG.grpc.BooleanMe" +
      "ssageB\020B\014ClientProtosP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_pt_groupG_grpc_JoinMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_pt_groupG_grpc_JoinMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pt_groupG_grpc_JoinMessage_descriptor,
        new java.lang.String[] { "Address", "Port", });
    internal_static_pt_groupG_grpc_BooleanMessage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_pt_groupG_grpc_BooleanMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pt_groupG_grpc_BooleanMessage_descriptor,
        new java.lang.String[] { "Value", });
    internal_static_pt_groupG_grpc_EmptyMessage_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_pt_groupG_grpc_EmptyMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pt_groupG_grpc_EmptyMessage_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
