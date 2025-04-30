package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new fudex.bonyad.DataBinderMapperImpl());
  }
}
