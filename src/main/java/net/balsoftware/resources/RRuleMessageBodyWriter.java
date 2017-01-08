//package net.balsoftware.resources;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//import java.util.List;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.MessageBodyWriter;
//import javax.ws.rs.ext.Provider;
//
//import net.balsoftware.bean.RRule;
// 
//@Provider
//@Produces(MediaType.TEXT_PLAIN)
//public class RRuleMessageBodyWriter implements MessageBodyWriter<List<RRule>>
//{
//	@Override
//	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long getSize(List<RRule> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeTo(List<RRule> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
//			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
//			throws IOException, WebApplicationException
//	{
////        StringBuilder sb = new StringBuilder();
////        sb.append("<boolean><boolean>").append(myBool.toString()).append("</boolean></boolean>");
//        DataOutputStream dos = new DataOutputStream(entityStream);
//        dos.writeUTF(sb.toString());
//	}
//
//  
////    @Override
////    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
////        System.out.println("isWriteable called...");
////        return type == Boolean.class;
////    }
////  
////    @Override
////    public long getSize(Boolean myBool, Class type, Type genericType,
////                        Annotation[] annotations, MediaType mediaType) {
////        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
////        return 0;
////    }
//  
////    @Override
////    public void writeTo(RRule myBool,
////                        Class type,
////                        Type genericType,
////                        Annotation[] annotations,
////                        MediaType mediaType,
////                        MultivaluedMap<string object=""> httpHeaders,
////                        OutputStream entityStream)
////                        throws IOException, WebApplicationException {
////  
////        StringBuilder sb = new StringBuilder();
////        sb.append("<boolean><boolean>").append(myBool.toString()).append("</boolean></boolean>");
////        DataOutputStream dos = new DataOutputStream(entityStream);
////        dos.writeUTF(sb.toString());
////    }
//}