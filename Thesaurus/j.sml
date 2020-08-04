fun f x =
      let fun h z =
            let fun r w = w*z
            in r(x+1)
            end
          fun g y = h(x+y)
      in g x
    end;

f 3;
