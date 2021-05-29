// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/proto/ClientProto.proto

package pt.groupG.grpc;

/**
 * Protobuf type {@code pt.groupG.grpc.NodeDetailsListMessage}
 */
public  final class NodeDetailsListMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pt.groupG.grpc.NodeDetailsListMessage)
    NodeDetailsListMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NodeDetailsListMessage.newBuilder() to construct.
  private NodeDetailsListMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NodeDetailsListMessage() {
    nodes_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NodeDetailsListMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              nodes_ = new java.util.ArrayList<pt.groupG.grpc.NodeDetailsMessage>();
              mutable_bitField0_ |= 0x00000001;
            }
            nodes_.add(
                input.readMessage(pt.groupG.grpc.NodeDetailsMessage.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        nodes_ = java.util.Collections.unmodifiableList(nodes_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pt.groupG.grpc.ClientProtos.internal_static_pt_groupG_grpc_NodeDetailsListMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pt.groupG.grpc.ClientProtos.internal_static_pt_groupG_grpc_NodeDetailsListMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pt.groupG.grpc.NodeDetailsListMessage.class, pt.groupG.grpc.NodeDetailsListMessage.Builder.class);
  }

  public static final int NODES_FIELD_NUMBER = 1;
  private java.util.List<pt.groupG.grpc.NodeDetailsMessage> nodes_;
  /**
   * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
   */
  public java.util.List<pt.groupG.grpc.NodeDetailsMessage> getNodesList() {
    return nodes_;
  }
  /**
   * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
   */
  public java.util.List<? extends pt.groupG.grpc.NodeDetailsMessageOrBuilder> 
      getNodesOrBuilderList() {
    return nodes_;
  }
  /**
   * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
   */
  public int getNodesCount() {
    return nodes_.size();
  }
  /**
   * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
   */
  public pt.groupG.grpc.NodeDetailsMessage getNodes(int index) {
    return nodes_.get(index);
  }
  /**
   * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
   */
  public pt.groupG.grpc.NodeDetailsMessageOrBuilder getNodesOrBuilder(
      int index) {
    return nodes_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < nodes_.size(); i++) {
      output.writeMessage(1, nodes_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < nodes_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, nodes_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pt.groupG.grpc.NodeDetailsListMessage)) {
      return super.equals(obj);
    }
    pt.groupG.grpc.NodeDetailsListMessage other = (pt.groupG.grpc.NodeDetailsListMessage) obj;

    boolean result = true;
    result = result && getNodesList()
        .equals(other.getNodesList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getNodesCount() > 0) {
      hash = (37 * hash) + NODES_FIELD_NUMBER;
      hash = (53 * hash) + getNodesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pt.groupG.grpc.NodeDetailsListMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pt.groupG.grpc.NodeDetailsListMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code pt.groupG.grpc.NodeDetailsListMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pt.groupG.grpc.NodeDetailsListMessage)
      pt.groupG.grpc.NodeDetailsListMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pt.groupG.grpc.ClientProtos.internal_static_pt_groupG_grpc_NodeDetailsListMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pt.groupG.grpc.ClientProtos.internal_static_pt_groupG_grpc_NodeDetailsListMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pt.groupG.grpc.NodeDetailsListMessage.class, pt.groupG.grpc.NodeDetailsListMessage.Builder.class);
    }

    // Construct using pt.groupG.grpc.NodeDetailsListMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getNodesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (nodesBuilder_ == null) {
        nodes_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        nodesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pt.groupG.grpc.ClientProtos.internal_static_pt_groupG_grpc_NodeDetailsListMessage_descriptor;
    }

    @java.lang.Override
    public pt.groupG.grpc.NodeDetailsListMessage getDefaultInstanceForType() {
      return pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance();
    }

    @java.lang.Override
    public pt.groupG.grpc.NodeDetailsListMessage build() {
      pt.groupG.grpc.NodeDetailsListMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public pt.groupG.grpc.NodeDetailsListMessage buildPartial() {
      pt.groupG.grpc.NodeDetailsListMessage result = new pt.groupG.grpc.NodeDetailsListMessage(this);
      int from_bitField0_ = bitField0_;
      if (nodesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          nodes_ = java.util.Collections.unmodifiableList(nodes_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.nodes_ = nodes_;
      } else {
        result.nodes_ = nodesBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pt.groupG.grpc.NodeDetailsListMessage) {
        return mergeFrom((pt.groupG.grpc.NodeDetailsListMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pt.groupG.grpc.NodeDetailsListMessage other) {
      if (other == pt.groupG.grpc.NodeDetailsListMessage.getDefaultInstance()) return this;
      if (nodesBuilder_ == null) {
        if (!other.nodes_.isEmpty()) {
          if (nodes_.isEmpty()) {
            nodes_ = other.nodes_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureNodesIsMutable();
            nodes_.addAll(other.nodes_);
          }
          onChanged();
        }
      } else {
        if (!other.nodes_.isEmpty()) {
          if (nodesBuilder_.isEmpty()) {
            nodesBuilder_.dispose();
            nodesBuilder_ = null;
            nodes_ = other.nodes_;
            bitField0_ = (bitField0_ & ~0x00000001);
            nodesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getNodesFieldBuilder() : null;
          } else {
            nodesBuilder_.addAllMessages(other.nodes_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pt.groupG.grpc.NodeDetailsListMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pt.groupG.grpc.NodeDetailsListMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pt.groupG.grpc.NodeDetailsMessage> nodes_ =
      java.util.Collections.emptyList();
    private void ensureNodesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        nodes_ = new java.util.ArrayList<pt.groupG.grpc.NodeDetailsMessage>(nodes_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pt.groupG.grpc.NodeDetailsMessage, pt.groupG.grpc.NodeDetailsMessage.Builder, pt.groupG.grpc.NodeDetailsMessageOrBuilder> nodesBuilder_;

    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public java.util.List<pt.groupG.grpc.NodeDetailsMessage> getNodesList() {
      if (nodesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(nodes_);
      } else {
        return nodesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public int getNodesCount() {
      if (nodesBuilder_ == null) {
        return nodes_.size();
      } else {
        return nodesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public pt.groupG.grpc.NodeDetailsMessage getNodes(int index) {
      if (nodesBuilder_ == null) {
        return nodes_.get(index);
      } else {
        return nodesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder setNodes(
        int index, pt.groupG.grpc.NodeDetailsMessage value) {
      if (nodesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNodesIsMutable();
        nodes_.set(index, value);
        onChanged();
      } else {
        nodesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder setNodes(
        int index, pt.groupG.grpc.NodeDetailsMessage.Builder builderForValue) {
      if (nodesBuilder_ == null) {
        ensureNodesIsMutable();
        nodes_.set(index, builderForValue.build());
        onChanged();
      } else {
        nodesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder addNodes(pt.groupG.grpc.NodeDetailsMessage value) {
      if (nodesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNodesIsMutable();
        nodes_.add(value);
        onChanged();
      } else {
        nodesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder addNodes(
        int index, pt.groupG.grpc.NodeDetailsMessage value) {
      if (nodesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNodesIsMutable();
        nodes_.add(index, value);
        onChanged();
      } else {
        nodesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder addNodes(
        pt.groupG.grpc.NodeDetailsMessage.Builder builderForValue) {
      if (nodesBuilder_ == null) {
        ensureNodesIsMutable();
        nodes_.add(builderForValue.build());
        onChanged();
      } else {
        nodesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder addNodes(
        int index, pt.groupG.grpc.NodeDetailsMessage.Builder builderForValue) {
      if (nodesBuilder_ == null) {
        ensureNodesIsMutable();
        nodes_.add(index, builderForValue.build());
        onChanged();
      } else {
        nodesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder addAllNodes(
        java.lang.Iterable<? extends pt.groupG.grpc.NodeDetailsMessage> values) {
      if (nodesBuilder_ == null) {
        ensureNodesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, nodes_);
        onChanged();
      } else {
        nodesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder clearNodes() {
      if (nodesBuilder_ == null) {
        nodes_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        nodesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public Builder removeNodes(int index) {
      if (nodesBuilder_ == null) {
        ensureNodesIsMutable();
        nodes_.remove(index);
        onChanged();
      } else {
        nodesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public pt.groupG.grpc.NodeDetailsMessage.Builder getNodesBuilder(
        int index) {
      return getNodesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public pt.groupG.grpc.NodeDetailsMessageOrBuilder getNodesOrBuilder(
        int index) {
      if (nodesBuilder_ == null) {
        return nodes_.get(index);  } else {
        return nodesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public java.util.List<? extends pt.groupG.grpc.NodeDetailsMessageOrBuilder> 
         getNodesOrBuilderList() {
      if (nodesBuilder_ != null) {
        return nodesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(nodes_);
      }
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public pt.groupG.grpc.NodeDetailsMessage.Builder addNodesBuilder() {
      return getNodesFieldBuilder().addBuilder(
          pt.groupG.grpc.NodeDetailsMessage.getDefaultInstance());
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public pt.groupG.grpc.NodeDetailsMessage.Builder addNodesBuilder(
        int index) {
      return getNodesFieldBuilder().addBuilder(
          index, pt.groupG.grpc.NodeDetailsMessage.getDefaultInstance());
    }
    /**
     * <code>repeated .pt.groupG.grpc.NodeDetailsMessage nodes = 1;</code>
     */
    public java.util.List<pt.groupG.grpc.NodeDetailsMessage.Builder> 
         getNodesBuilderList() {
      return getNodesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pt.groupG.grpc.NodeDetailsMessage, pt.groupG.grpc.NodeDetailsMessage.Builder, pt.groupG.grpc.NodeDetailsMessageOrBuilder> 
        getNodesFieldBuilder() {
      if (nodesBuilder_ == null) {
        nodesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pt.groupG.grpc.NodeDetailsMessage, pt.groupG.grpc.NodeDetailsMessage.Builder, pt.groupG.grpc.NodeDetailsMessageOrBuilder>(
                nodes_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        nodes_ = null;
      }
      return nodesBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:pt.groupG.grpc.NodeDetailsListMessage)
  }

  // @@protoc_insertion_point(class_scope:pt.groupG.grpc.NodeDetailsListMessage)
  private static final pt.groupG.grpc.NodeDetailsListMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pt.groupG.grpc.NodeDetailsListMessage();
  }

  public static pt.groupG.grpc.NodeDetailsListMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NodeDetailsListMessage>
      PARSER = new com.google.protobuf.AbstractParser<NodeDetailsListMessage>() {
    @java.lang.Override
    public NodeDetailsListMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NodeDetailsListMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NodeDetailsListMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NodeDetailsListMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public pt.groupG.grpc.NodeDetailsListMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

