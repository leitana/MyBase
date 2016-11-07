package lx_base.mybase.common.base;

import android.util.SparseArray;
import android.view.View;

/**
 * <Pre>
 * 通用viewHolder类,使用散列数组存储view对象,简化getView操作
 * </Pre>
 */
public class ViewHolder {

    /**
     * 快速获取以findViewById取得的子view的实例
     * @param v     被获取的view
     * @param id    子view的id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View v, int id){

        if(v == null){
            return null;
        }

        SparseArray<View> holder = (SparseArray<View>) v.getTag();
        if(holder == null){
            holder = new SparseArray<View>();
            v.setTag(holder);
        }

        View childView = holder.get(id);
        if(childView == null){
            childView = v.findViewById(id);
            holder.put(id,childView);
        }

        return (T) childView;
    }

}
