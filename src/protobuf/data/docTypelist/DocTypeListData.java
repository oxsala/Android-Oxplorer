// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Protobuf/docTypeList.proto

package protobuf.data.docTypelist;

public final class DocTypeListData {
  private DocTypeListData() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DocTypeIdListOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // repeated string docTypeId = 1;
    java.util.List<String> getDocTypeIdList();
    int getDocTypeIdCount();
    String getDocTypeId(int index);
  }
  public static final class DocTypeIdList extends
      com.google.protobuf.GeneratedMessage
      implements DocTypeIdListOrBuilder {
    // Use DocTypeIdList.newBuilder() to construct.
    private DocTypeIdList(Builder builder) {
      super(builder);
    }
    private DocTypeIdList(boolean noInit) {}
    
    private static final DocTypeIdList defaultInstance;
    public static DocTypeIdList getDefaultInstance() {
      return defaultInstance;
    }
    
    public DocTypeIdList getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.data.docTypelist.DocTypeListData.internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.data.docTypelist.DocTypeListData.internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_fieldAccessorTable;
    }
    
    // repeated string docTypeId = 1;
    public static final int DOCTYPEID_FIELD_NUMBER = 1;
    private com.google.protobuf.LazyStringList docTypeId_;
    public java.util.List<String>
        getDocTypeIdList() {
      return docTypeId_;
    }
    public int getDocTypeIdCount() {
      return docTypeId_.size();
    }
    public String getDocTypeId(int index) {
      return docTypeId_.get(index);
    }
    
    private void initFields() {
      docTypeId_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < docTypeId_.size(); i++) {
        output.writeBytes(1, docTypeId_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < docTypeId_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(docTypeId_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getDocTypeIdList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.data.docTypelist.DocTypeListData.DocTypeIdList parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.data.docTypelist.DocTypeListData.DocTypeIdList prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements protobuf.data.docTypelist.DocTypeListData.DocTypeIdListOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.data.docTypelist.DocTypeListData.internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.data.docTypelist.DocTypeListData.internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_fieldAccessorTable;
      }
      
      // Construct using docTypelist.data.protobuf.DocTypeListData.DocTypeIdList.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        docTypeId_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.data.docTypelist.DocTypeListData.DocTypeIdList.getDescriptor();
      }
      
      public protobuf.data.docTypelist.DocTypeListData.DocTypeIdList getDefaultInstanceForType() {
        return protobuf.data.docTypelist.DocTypeListData.DocTypeIdList.getDefaultInstance();
      }
      
      public protobuf.data.docTypelist.DocTypeListData.DocTypeIdList build() {
        protobuf.data.docTypelist.DocTypeListData.DocTypeIdList result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private protobuf.data.docTypelist.DocTypeListData.DocTypeIdList buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        protobuf.data.docTypelist.DocTypeListData.DocTypeIdList result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public protobuf.data.docTypelist.DocTypeListData.DocTypeIdList buildPartial() {
        protobuf.data.docTypelist.DocTypeListData.DocTypeIdList result = new protobuf.data.docTypelist.DocTypeListData.DocTypeIdList(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          docTypeId_ = new com.google.protobuf.UnmodifiableLazyStringList(
              docTypeId_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.docTypeId_ = docTypeId_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.data.docTypelist.DocTypeListData.DocTypeIdList) {
          return mergeFrom((protobuf.data.docTypelist.DocTypeListData.DocTypeIdList)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(protobuf.data.docTypelist.DocTypeListData.DocTypeIdList other) {
        if (other == protobuf.data.docTypelist.DocTypeListData.DocTypeIdList.getDefaultInstance()) return this;
        if (!other.docTypeId_.isEmpty()) {
          if (docTypeId_.isEmpty()) {
            docTypeId_ = other.docTypeId_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureDocTypeIdIsMutable();
            docTypeId_.addAll(other.docTypeId_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              ensureDocTypeIdIsMutable();
              docTypeId_.add(input.readBytes());
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // repeated string docTypeId = 1;
      private com.google.protobuf.LazyStringList docTypeId_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureDocTypeIdIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          docTypeId_ = new com.google.protobuf.LazyStringArrayList(docTypeId_);
          bitField0_ |= 0x00000001;
         }
      }
      public java.util.List<String>
          getDocTypeIdList() {
        return java.util.Collections.unmodifiableList(docTypeId_);
      }
      public int getDocTypeIdCount() {
        return docTypeId_.size();
      }
      public String getDocTypeId(int index) {
        return docTypeId_.get(index);
      }
      public Builder setDocTypeId(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureDocTypeIdIsMutable();
        docTypeId_.set(index, value);
        onChanged();
        return this;
      }
      public Builder addDocTypeId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureDocTypeIdIsMutable();
        docTypeId_.add(value);
        onChanged();
        return this;
      }
      public Builder addAllDocTypeId(
          java.lang.Iterable<String> values) {
        ensureDocTypeIdIsMutable();
        super.addAll(values, docTypeId_);
        onChanged();
        return this;
      }
      public Builder clearDocTypeId() {
        docTypeId_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      void addDocTypeId(com.google.protobuf.ByteString value) {
        ensureDocTypeIdIsMutable();
        docTypeId_.add(value);
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:com.oxseed.saas.archive.ocr.servlet.data.DocTypeIdList)
    }
    
    static {
      defaultInstance = new DocTypeIdList(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:com.oxseed.saas.archive.ocr.servlet.data.DocTypeIdList)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032Protobuf/docTypeList.proto\022(com.oxseed" +
      ".saas.archive.ocr.servlet.data\"\"\n\rDocTyp" +
      "eIdList\022\021\n\tdocTypeId\030\001 \003(\tB,\n\031docTypelis" +
      "t.data.protobufB\017DocTypeListData"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_oxseed_saas_archive_ocr_servlet_data_DocTypeIdList_descriptor,
              new java.lang.String[] { "DocTypeId", },
              protobuf.data.docTypelist.DocTypeListData.DocTypeIdList.class,
              protobuf.data.docTypelist.DocTypeListData.DocTypeIdList.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}